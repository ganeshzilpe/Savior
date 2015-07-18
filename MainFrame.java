import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

class MainFrame extends JFrame implements ActionListener
{
    JButton ba,bm,bs,bd,bh,bc,bab;
    ImageIcon ii;
    Image i;

    MainFrame(String s)
    {
        super(s);
        JPanel p=new JPanel();
        JPanel q=new JPanel();
        ii=new ImageIcon("G:\\TE-I\\Project\\BloodBankManagement\\dist\\lib\\BD.jpg");
        i=ii.getImage();
        JLabel j=new JLabel()
        {
            public void paint(Graphics g)
            {
                g.drawImage(i,0,0,this);
            }
        };
        j.setBounds(0,100,5,5);
        ba=new JButton("ADD ENTRY");
        ba.addActionListener(this);
        bm=new JButton("MODIFY ENTRY");
        bm.addActionListener(this);
        bs=new JButton("SEARCH");
        bs.addActionListener(this);
        bd=new JButton("DELETE");
        bd.addActionListener(this);
        bab=new JButton("ABOUT");
        bab.addActionListener(this);
        bc=new JButton("CLOSE");
        bc.addActionListener(this);
        bh=new JButton("HELP");
        bh.addActionListener(this);
        p.setLayout(new GridLayout(1,4,5,5));
        p.setBounds(0,0,1350,50);
        p.add(ba);
        p.add(bm);
        p.add(bd);
        p.add(bs);
        q.setLayout(new GridLayout(1,3,20,20));
        q.setBounds(0,650,1367,50);
        q.add(bab);
        q.add(bh);
        q.add(bc);
        add(p);
        add(q);
        JPanel r=new JPanel();
        add(r,BorderLayout.CENTER);
        add(j);
        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                System.exit(0);
            }
        });
        setSize(1367,737);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e)
    {
        JButton b=(JButton)e.getSource();
        if(b==ba)
        {
            donForm df=new donForm("ENTRY FORM");
        }
        if(b==bm)
        {
            modForm mf=new modForm("MODIFY ENTRY");
        }
        if(b==bd)
        {
            delForm dl=new delForm("DELETE ENTRY");
        }
        if(b==bc)
        {
            int opn=0;
            String []opt={"NO","YES"};
            while(true)
            {
                opn=JOptionPane.showOptionDialog(null,"Are you sure you want to quit the BloodBank?","Confirmation",JOptionPane.NO_OPTION,JOptionPane.INFORMATION_MESSAGE,null,opt,0);
                if(opn==0)
                    break;
                else
                {
                    System.exit(0);
                }
            }
        }
        if(b==bs)
        {
            srcForm sf=new srcForm("SEARCH");
        }
        /*if(b==bh)
        {
            help h=new help("Help");
        }
        if(b==ba)
        {
            about a=new about("About");
        }*/
    }
}
