import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormHome extends JDialog {
    private JPanel JHome;
    private JButton ajouterUser;
    private JButton connecterUser;
    private JTextField textField1;
    private JTextField textField2;
    private JButton supprimerButton;
    private JLabel jlPrenoms;
    private JLabel jlNoms;
    private JButton modifierUser;
    private JButton supprimerUser;

    public FormHome(JDialog parent){
        super(parent);
        setTitle("Fenêtre Home");
        setContentPane(JHome);
        setMaximumSize(new Dimension(800,600));
        setMinimumSize(new Dimension(800,600));
        setModal(false);
        setVisible(true);


        //Connecter un utilisateur
        connecterUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        //Ajouter un nouvel utilisateur
        ajouterUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        //Supprimer un utilisateur
        supprimerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        //Modifier un utilisateur
        modifierUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //User user = new User(nom, prenom, email, password);
                //FormUser formUSer = new FormUser(user);
            }
        });
    }
}
