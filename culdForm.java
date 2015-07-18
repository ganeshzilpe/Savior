import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.WindowAdapter;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

class culdForm extends JFrame implements ActionListener,FocusListener
{
    JLabel date,cnt,bb1,bb2,bb3,bb4,bb5;
    JTextField tcnt,tbb1,tbb2,tbb3,tbb4,tbb5;
    Choice cdt,cmnth,cyr;
    JButton add,close;
    JPanel p,q;

    culdForm(String s,int a,int b,int c,String sbb1)
    {
        super(s);
        // labels
        date=new JLabel("Date :-");
        date.setBounds(70,35,70,30);
        cnt=new JLabel("Count :-");
        cnt.setBounds(70,100,70,30);
        bb1=new JLabel("Blood Bank 1 :-");
        bb1.setBounds(70,170,90,30);
        bb2=new JLabel("Blood Bank 2 :-");
        bb2.setBounds(70,245,90,30);
        bb3=new JLabel("Blood Bank 3 :-");
        bb3.setBounds(70,320,90,30);
        bb4=new JLabel("Blood Bank 4 :-");
        bb4.setBounds(70,395,90,30);
        bb5=new JLabel("Blood Bank 5 :-");
        bb5.setBounds(70,470,90,30);
        
        cdt=new Choice();
        cdt.add("DD");
        cdt.add("01");
        cdt.add("02");
        cdt.add("03");
        cdt.add("04");
        cdt.add("05");
        cdt.add("06");
        cdt.add("07");
        cdt.add("08");
        cdt.add("09");
        for(int i=10;i<=31;i++)
            cdt.add(""+i);
        cdt.setBounds(120,37,50,30);
        if(a!=0 && a<10)
            cdt.select("0"+a);
        else
            cdt.select(""+a);
        cmnth=new Choice();
        cmnth.add("MM");
        cmnth.add("01");
        cmnth.add("02");
        cmnth.add("03");
        cmnth.add("04");
        cmnth.add("05");
        cmnth.add("06");
        cmnth.add("07");
        cmnth.add("08");
        cmnth.add("09");
        for(int i=10;i<=12;i++)
            cmnth.add(""+i);
        cmnth.setBounds(180,37,50,30);
        if(b!=0 && b<10)
            cmnth.select("0"+b);
        else
            cmnth.select(""+b);
        cyr=new Choice();
        cyr.add("YYYY");
        for(int i=1975;i<=2100;i++)
            cyr.add(""+i);
        cyr.setBounds(240,37,90,30);
        if(c!=0)
            cyr.select(""+c);
        tcnt=new JTextField();
        tcnt.setBounds(130,101,50,25);
        tcnt.addFocusListener(this);
        tcnt.setEditable(false);
        tbb1=new JTextField();
        tbb1.setBounds(165,172,200,25);
        tbb1.addActionListener(this);
        tbb1.setText(sbb1);
        tbb2=new JTextField();
        tbb2.setBounds(165,246,200,25);
        tbb2.addActionListener(this);
        tbb3=new JTextField();
        tbb3.setBounds(165,322,200,25);
        tbb3.addActionListener(this);
        tbb4=new JTextField();
        tbb4.setBounds(165,396,200,25);
        tbb4.addFocusListener(this);
        tbb5=new JTextField();
        tbb5.setBounds(165,472,200,25);
        tbb5.addActionListener(this);

        add(date);add(cdt);add(cmnth);add(cyr);
        add(cnt);add(tcnt);
        add(bb1);add(tbb1);
        add(bb2);add(tbb2);
        add(bb3);add(tbb3);
        add(bb4);add(tbb4);
        add(bb5);add(tbb5);
        
        add=new JButton("ADD");
        add.addActionListener(this);
        close=new JButton("CLOSE");
        close.addActionListener(this);
        q=new JPanel();
        p=new JPanel();
        q.setLayout(new GridLayout(5,4,70,70));
        p.add(add);
        p.add(close);
        p.setLayout(new GridLayout(1,4,5,5));

        add(q,BorderLayout.CENTER);
        add(p,BorderLayout.SOUTH);
        addWindowListener(new WindowAdapter()
        {
            public void windowClosing()
            {
                setVisible(false);
            }
        });
        setBounds(430,50,520,600);
        setVisible(true);
    }
    public void focusGained(FocusEvent e)
    {
        JTextField t=(JTextField)e.getSource();
        if(t==tcnt)
        {
            Culdesac.dt=Integer.parseInt(cdt.getSelectedItem());
            Culdesac.mnth=Integer.parseInt(cmnth.getSelectedItem());
            Culdesac.yr=Integer.parseInt(cyr.getSelectedItem());
            ResultSet rs1=null;
            try
            {
                rs1=Culdesac.csearch();
                if(rs1.next())
                {
                    tcnt.setText(""+rs1.getInt(1));
                }
            }
            catch(Exception e34){}
        }
    }

    public void focusLost(FocusEvent e){}

    public void itemStateChanged(ItemEvent n){}

    public void actionPerformed(ActionEvent e)
    {
        JButton b=(JButton)e.getSource();
        try
        {
            Culdesac.dt=Integer.parseInt(cdt.getSelectedItem());
            Culdesac.mnth=Integer.parseInt(cmnth.getSelectedItem());
            Culdesac.yr=Integer.parseInt(cyr.getSelectedItem());
            Culdesac.count=Integer.parseInt(tcnt.getText());
        }
        catch(Exception ew)
        {}
        Culdesac.bb1=tbb1.getText();
        Culdesac.bb2=tbb2.getText();
        Culdesac.bb3=tbb3.getText();
        Culdesac.bb4=tbb4.getText();
        Culdesac.bb5=tbb5.getText();
        if(b==add)
        {
            if(cdt.getSelectedItem().equals("DD")||cmnth.getSelectedItem().equals("MM")||cyr.getSelectedItem().equals("YYYY")||tcnt.getText().equals("")||bb1.getText().equals(""))
            {
                JOptionPane.showMessageDialog(null,"All FIELDS are COMPULSORY.");
                return;
            }
            if(tcnt.getText().equals("0"))
            {
                JOptionPane.showMessageDialog(null,"Invalid Summary Record.");
                tcnt.setText("");
                tbb1.setText("");
                tbb2.setText("");
                tbb3.setText("");
                tbb4.setText("");
                tbb5.setText("");
                cdt.requestFocus();
                return;
            }
            try
            {
                Culdesac.cadd();
            }
            catch(Exception e1){}
        }
        if(b==close)
        {
            int opn=0;
            String []opt={"NO","YES"};
            while(true)
            {
                opn=JOptionPane.showOptionDialog(null,"Are you sure you want to quit the Summary Form and return to Main Menu?","Confirmation",JOptionPane.NO_OPTION,JOptionPane.INFORMATION_MESSAGE,null,opt,0);
                if(opn==0)
                    break;
                else
                {
                    setVisible(false);
                    break;
                }
            }
        }
        cdt.select("");
        cmnth.select("");
        cyr.select("");
        tcnt.setText("");
        tbb1.setText("");
        tbb2.setText("");
        tbb3.setText("");
        tbb4.setText("");
        tbb5.setText("");
        cdt.requestFocus();
    }
}