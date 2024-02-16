import com.groupe3.user.User;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.groupe3.user.UserManager.findUserByEmail;

public class FormUser extends JDialog {
    private JTextField tfNom;
    private JTextField tfPrenom;
    private JTextField tfEmail;
    private JButton jbAdd;
    private JLabel jlNom;
    private JLabel jlPrenom;
    private JLabel jlEmail;
    private JLabel jlPassword;
    private JPanel jpMain;
    private JPasswordField pfPassword;

    public FormUser(JDialog parent) {
        //surcharger la méthode
        super(parent);
        //Donner un titre à la fenêtre
        setTitle("Ma génial fenêtre");
        //setter le container
        setContentPane(jpMain);
        //sélectionner la taille de la fenêtre en pixel
        setMinimumSize(new Dimension(450, 600));
        //choisir si c'est un modal
        setModal(false);
        //indiquer si la fenêtre est visible ou pas au chargement
        setVisible(true);


        jbAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addOrUpdate(tfNom, tfPrenom, tfEmail, pfPassword, parent, true);
            }
        });
    }

/** METHODE*/

    /** Fonction pour ajouter OU maj un User*/
    public void addOrUpdate(JTextField nom, JTextField prenom, JTextField email, JPasswordField password, JDialog parent, boolean ajoutOuMaj){
        /** Si les champs sont vides on affiche un message*/
        if (nom.getText().isEmpty() || prenom.getText().isEmpty() || email.getText().isEmpty() || String.valueOf(password.getPassword()).isEmpty()) {
            afficherMessage(parent, "Veuillez remplir tous les champs du formulaire","Essaie encore", true );
            return;
        } else {
            /** si l'email est incorrect on affiche un message*/
            String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(email.getText());
            if (!matcher.matches()) {
                afficherMessage(parent, "Le mail n'est pas un mail m'enfin !", "Mail Incorrect", true);
                return;
            } else {
                /** si le mot de passe est incorrect on affiche un message*/
                String mdp = String.valueOf(password.getPassword());

                /**mini 12 caractères + un chiffre + uppercase + lowcase*/
                String regexP = "^(?=.[a-z].*[A-Z].*\\d).{12,}$";

                if (!mdp.matches(regexP)) {
                    afficherMessage(parent, "Le mot de passe est incorrect", "Mot de passe incorrect", true);
                    return;
                }
                else {
                    /** création d'un nouvel objet User*/
                    String pw_hash = BCrypt.hashpw( String.valueOf(password.getPassword()), BCrypt.gensalt());

                    User nvUser = new User(nom.getText(), prenom.getText(), email.getText(), pw_hash);
                    System.out.println(String.valueOf(password.getPassword()));

                    /**BOUTON MODIFIER*/
                    if(!ajoutOuMaj){
                        //Vérifie si user existe en BDD
                        if(findUserByEmail(nvUser).getNom() != null){
                            /** Modification du nouvel objet User dans la BDD*/
                            com.groupe3.user.UserManager.updateUser(nvUser);

                            /** message pour dire que c'est good pour modification*/
                            afficherMessage(parent,"Le compte de " + nvUser.getPrenom() + " a bien été modifié en BDD", "MODIFICATION", false );
                            return;
                        }else {
                            afficherMessage(parent, "Le compte n'existe pas en BDD, il faut le créer", "Connaissez vous notre sauveur ?", true);
                            return;
                        }

                        /**BOUTON AJOUTER*/
                    } else if (ajoutOuMaj) {
                        //Vérifie si user N'existe PAS en BDD
                        if (findUserByEmail(nvUser).getNom() == null){
                            /** Ajout du nouvel objet User dans la BDD*/
                            com.groupe3.user.UserManager.addUser(nvUser);

                            /** message pour dire que c'est good*/
                            afficherMessage(parent,"Le compte de " + nvUser.getPrenom() + " a bien été ajouté en BDD", "Bravo!", false );
                            return;

                        }else {
                            afficherMessage(parent, "Le compte de " + nvUser.getPrenom() + " existe déjà en BDD", "NON!", true);
                            return;

                        }
                    }
                }
            }
        }
    }
    public void afficherMessage(JDialog parent, String content, String title, boolean type){
        JOptionPane.showMessageDialog(parent,
                content,
                title,
                type? JOptionPane.ERROR_MESSAGE : JOptionPane.INFORMATION_MESSAGE
        );
    }
}