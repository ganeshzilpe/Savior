import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

class LoginForm extends JFrame implements ActionListener
{
    JLabel usr,pass;
    JPasswordField pwd;
    JTextField tusr;
    JButton ok,can,chng;
    JPanel p;
    LoginForm(String s)
    {
        super(s);
        p=new JPanel();
        usr=new JLabel("User Name:");
        pass=new JLabel("Password:");
        tusr=new JTextField();
        tusr.setBounds(400,400,50,60);
        ok=new JButton("OK");
        ok.addActionListener(this);
        can=new JButton("CANCLE");
        can.addActionListener(this);
        chng=new JButton("MODIFY");
        chng.addActionListener(this);
        p.setLayout(null);
        p.setBounds(0,0,1350,50);
        p.add(ok);
        p.add(can);
        p.add(chng);
        add(tusr);

        setVisible(true);
        setBounds(0,0,1350,800);

    }

    public void actionPerformed(ActionEvent er)
    {
        JButton b=(JButton)er.getSource();
        if(b==ok)
        {
            MainFrame mf=new MainFrame("Welcome");
        }
    }
}