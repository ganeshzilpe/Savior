import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Choice;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

class panel2 extends JPanel implements ActionListener, FocusListener, ItemListener
{
    JLabel head,did,dnm,date,blg,gen,age,wt,admyr,cno,email,cnt;
    JTextField tid,tnm,tage,twt,tcno,temail,tcnt;
    Choice cdt,cmnth,cyr,cblg,cdom,cadmyr;
    CheckboxGroup gn;
    Checkbox ml,fl;
    JButton mod,close,rfrsh;
    modForm m;

    panel2(modForm m2)
    {
        m=m2;
        setLayout(null);
        dnm=new JLabel("Name :-");
        dnm.setBounds(90,35,70,30);
        did=new JLabel("Donor ID :-");
        did.setBounds(90,100,70,30);
        date=new JLabel("Date :-");
        date.setBounds(340,100,70,30);
        blg=new JLabel("Blood Group :-");
        blg.setBounds(90,165,90,30);
        gen=new JLabel("Gender :-");
        gen.setBounds(340,165,70,30);
        age=new JLabel("Age :-");
        age.setBounds(90,240,70,30);
        wt=new JLabel("Weight :-");
        wt.setBounds(340,240,70,30);
        admyr=new JLabel("Admission Year :-");
        admyr.setBounds(90,315,100,30);
        cno=new JLabel("Contact NO :-");
        cno.setBounds(90,390,90,30);
        email=new JLabel("E-mail Address :-");
        email.setBounds(90,465,100,30);
        cnt=new JLabel("Frequency :-");
        cnt.setBounds(90,530,70,30);

        // Textfield
        tnm=new JTextField();
        tnm.setBounds(145,40,490,23);
        tid=new JTextField();
        tid.setBounds(160,105,60,23);
        tid.setEditable(false);
        tage=new JTextField();
        tage.setBounds(135,245,60,23);
        twt=new JTextField();
        twt.setBounds(405,245,65,23);
        tcno=new JTextField();
        tcno.setBounds(175,395,250,23);
        temail=new JTextField();
        temail.setBounds(195,469,250,23);
        tcnt=new JTextField();
        tcnt.setBounds(170,535,60,23);
        tcnt.setEditable(false);

        // Checkbox
        gn=new CheckboxGroup();
        ml=new Checkbox("Male",true,gn);
        ml.setBounds(405,160,70,40);
        fl=new Checkbox("Female",true,gn);
        fl.setBounds(475,160,90,40);

        // here are the choices
        cdt=new Choice();
        cdt.setEnabled(false);
        cdt.add("DD");
        for(int i1=1;i1<=31;i1++)
            cdt.add(""+i1);
        cdt.setBounds(390,105,50,35);
        cmnth=new Choice();
        cmnth.setEnabled(false);
        cmnth.add("MM");
        for(int i2=1;i2<=12;i2++)
            cmnth.add(""+i2);
        cmnth.setBounds(460,105,50,35);
        cyr=new Choice();
        cyr.setEnabled(false);
        cyr.add("YYYY");
        for(int i3=1975;i3<=2100;i3++)
            cyr.add(""+i3);
        cyr.setBounds(530,105,100,35);
        cblg=new Choice();
        cblg.add("Blood Group");
        cblg.add("A+");
        cblg.add("A-");
        cblg.add("B+");
        cblg.add("B-");
        cblg.add("AB+");
        cblg.add("AB-");
        cblg.add("O+");
        cblg.add("O-");
        cblg.setBounds(180,169,100,35);
        cadmyr=new Choice();
        cadmyr.add("YYYY");
        for(int i4=1975;i4<=2100;i4++)
            cadmyr.add(""+i4);
        cadmyr.setBounds(205,319,100,35);
        cdom=new Choice();
        cdom.setEnabled(true);
        cdom.add("Domain Name");
        cdom.add("@gmail.com");
        cdom.add("@rediff.com");
        cdom.add("@gmail.co.in");
        cdom.add("@yahoo.com");
        cdom.add("@hotmail.com");
        cdom.add("");
        cdom.setBounds(455,469,120,40);

        mod=new JButton("MODIFY ENTRY");
        mod.addActionListener(this);
        mod.setBounds(150,605,220,28);
        close=new JButton("CLOSE");
        close.addActionListener(this);
        close.setBounds(380,605,220,28);
        rfrsh=new JButton("REFRESH");
        rfrsh.addActionListener(this);
        rfrsh.setBounds(610,605,220,28);
        add(mod);add(close);add(rfrsh);

        tid.addFocusListener(this);
        tnm.addFocusListener(this);
        tage.addFocusListener(this);
        twt.addFocusListener(this);
        tcno.addFocusListener(this);
        temail.addFocusListener(this);
        tcnt.addFocusListener(this);

        //added to the panel
        add(dnm);add(tnm);
        add(did);add(tid);add(date);add(cdt);add(cmnth);add(cyr);
        add(blg);add(cblg);add(gen);add(ml);add(fl);
        add(age);add(tage); add(wt);add(twt);
        add(admyr);add(cadmyr); 
        add(cno);add(tcno);
        add(email);add(temail); add(cdom);
        add(cnt);add(tcnt);
        add(mod);add(close);
    }

    public void actionPerformed(ActionEvent e)
    {
        JButton b=(JButton)e.getSource();
        Checkbox g=gn.getSelectedCheckbox();
        try
        {
            modify.name=tnm.getText();
            modify.did=Integer.parseInt(tid.getText());
            modify.dt=Integer.parseInt(cdt.getSelectedItem());
            modify.mnth=Integer.parseInt(cmnth.getSelectedItem());
            modify.yr=Integer.parseInt(cyr.getSelectedItem());
            modify.blg=cblg.getSelectedItem();
            modify.gen=g.getLabel();
            modify.age=Integer.parseInt(tage.getText());
            modify.wt=Integer.parseInt(twt.getText());
            modify.admyr=Integer.parseInt(cadmyr.getSelectedItem());
            modify.cno=tcno.getText();
            modify.email=temail.getText()+cdom.getSelectedItem();
            modify.cnt=Integer.parseInt(tcnt.getText());
        }
        catch(Exception e4){}
        if(b==mod)
        {
            if(tnm.getText().equals("")||twt.getText().equals("")||cblg.getSelectedItem().equals("Blood Group")||cdt.getSelectedItem().equals("DD")||cmnth.getSelectedItem().equals("MM")||cyr.getSelectedItem().equals("YYYY")||cadmyr.getSelectedItem().equals("YYYY")||tage.getText().equals("")||tcno.getText().equals("")||temail.getText().equals("")||tcnt.getText().equals(""))
            {
                JOptionPane.showMessageDialog(null,"All FIELDS are COMPULSORY.");
                tnm.requestFocus();
                return;
            }
            try
            {
                modify.dmod();
                modify.name="";
                modify.did=Integer.parseInt("");
                modify.dt=Integer.parseInt("");
                modify.mnth=Integer.parseInt("");
                modify.yr=Integer.parseInt("");
                modify.blg="";
                modify.gen="";
                modify.age=Integer.parseInt("");
                modify.wt=Integer.parseInt("");
                modify.admyr=Integer.parseInt("");
                modify.cno="";
                modify.email="";
                modify.cnt=Integer.parseInt("");
            }
            catch(Exception e2){}
        }
        if(b==close)
        {
            int opn=0;
            String []opt={"NO","YES"};
            while(true)
            {
                opn=JOptionPane.showOptionDialog(null,"Are you sure you want to quit the MODIFY ENTRY FORM?","Confirmation",JOptionPane.NO_OPTION,JOptionPane.INFORMATION_MESSAGE,null,opt,0);
                if(opn==0)
                    break;
                else
                {
                    m.setVisible(false);
                    break;
                }
            }
        }
        if(b==rfrsh)
        {
            m.setVisible(false);
            modForm mf=new modForm("MODIFY ENTRY");
        }
        tnm.setText("");
        tid.setText("");
        cdt.select("DD");
        cmnth.select("MM");
        cyr.select("YYYY");
        cblg.select("Blood Group");
        ml.setState(true);
        tage.setText("");
        twt.setText("");
        tcno.setText("");
        temail.setText("");
        tcnt.setText("");
        cdom.select("Domain Name");
        tnm.requestFocus();
    }

    public void itemStateChanged(ItemEvent n){}

    public void focusGained(FocusEvent e)
    {
        JTextField t=(JTextField)e.getSource();
        if(t==tnm)
        {
            tid.setText(""+modify.did);
            tnm.setText(modify.name);
            cblg.select(""+modify.blg);
            tage.setText(""+modify.age);
            twt.setText(""+modify.wt);
            tcno.setText(modify.cno);

            String s1=modify.email;
            String s2="",s3="";
            char ch[]=null;
            int l=s1.length();
            for(int i=1;i<l;i++)
            {
                if(s1.charAt(i)=='@')
                {
                    s3=s1.substring(i,l);
                    s2=s1.substring(0,i);
                    break;
                }
            }
            temail.setText(s2);
            cdom.select(s3);

            cadmyr.select(""+modify.admyr);
            if(modify.gen.equals("Male"))
                gn.setSelectedCheckbox(ml);
            else
                gn.setSelectedCheckbox(fl);
            tcnt.setText(""+modify.cnt);
            cdt.select(""+modify.dt);
            cmnth.select(""+modify.mnth);
            cyr.select(""+modify.yr);
        }
    }

    public void focusLost(FocusEvent e)
    {}
}