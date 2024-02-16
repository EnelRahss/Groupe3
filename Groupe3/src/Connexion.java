import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Connexion extends JDialog{
    private JTextField tfEmail;
    private JLabel jlEmail;
    private JLabel jlPwd;
    private JPasswordField pfPwd;
    private JButton btConnexion;
    private JPanel jpConnexion;

    public Connexion(JFrame parent) {
        super(parent);
        setTitle("Connexion");
        setMinimumSize(new Dimension(300, 300));
        setContentPane(jpConnexion);
        setLocationRelativeTo(parent);
        setModal(true);
        setVisible(true);
        btConnexion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {



            }
        });
    }

    public void connexionUser ( JDialog parent , boolean comportement){
        String message = "";
        String titre = "Erreur";
        boolean type = false;

        String email= tfEmail.getText();

        String password = String.valueOf(pfPwd.getPassword());
        //test si les champs ne sont pas remplis
        if( email.isEmpty() || password.isEmpty()){
            message = "Veuillez remplir tous les champs du formulaire";
        }//sinon les champs sont tous remplis
        else{

        }


    }

    
}
