package com.example.zunairazamanchaudh.candidateengine.ResumeManager;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.zunairazamanchaudh.candidateengine.R;
import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CVDashBoard extends AppCompatActivity implements View.OnClickListener {
  //pdf storage code
    private static final int STORAGE_CODE=1000;
    //Fonts
    private static String FILE = "c:/temp/FirstPdf.pdf";
    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);
    private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.NORMAL, BaseColor.RED);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
            Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.BOLD);

    private static final int Photo_ACTIVITY_REQUEST_CODE = 0;

    private static final int Ref_ACTIVITY_REQUEST_CODE = 1;
    private static final int Obj_ACTIVITY_REQUEST_CODE = 2;
    private static final int ACT_ACTIVITY_REQUEST_CODE = 3;
    private static final int Str_ACTIVITY_REQUEST_CODE = 4;
    private static final int Award_ACTIVITY_REQUEST_CODE = 5;
    private static final int Industry_ACTIVITY_REQUEST_CODE = 6;
    private static final int CONTACT_ACTIVITY_REQUEST_CODE = 7;
    private static final int Interest_ACTIVITY_REQUEST_CODE = 8;
    private static final int Project_ACTIVITY_REQUEST_CODE = 9;
    private static final int SKILL_ACTIVITY_REQUEST_CODE = 10;
    private static final int EXP_ACTIVITY_REQUEST_CODE = 11;
    private static final int EDU_ACTIVITY_REQUEST_CODE = 12;
    private static final int PERSONAL_ACTIVITY_REQUEST_CODE = 13;
    Button btnGenerateResume;
    String returnFoto,returnSign;
    String pi1,pi2,pi3,pi4;
    String returnCourse,returngrade,returnSchool,returnPassyear;
    String expOrg,expDesignation,expRole,expstatus;
    String returnStringn,returnString1,returnString2,returnString3,returnString4;
    String returnobj,returndate,returnplace;
    String returnH1,returnH2,returnH3,returnH4,returnH5;
    String returnStrenth1,returnStrenth2,returnStrenth3,returnStrenth4,returnStrenth5;
    String returnaward1,returnaward2,returnaward3,returnaward4,returnaward5;
    String returnIndustry1,returnIndustry2,returnIndustry3;
    String returnName,returnAdress,returnEmail,returnPhone;
    String returnInterest1,returnInterest2,returnInterest3,returnInterest4,returnInterest5,returnInterest6,returnInterest7;
    String returntitle,returnDescr,returnRole,returnTeam,returnDuration;
    String returnSkill1,returnSkill2,returnSkill3,returnSkill4,returnSkill5,returnSkill6,returnSkill7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cvdash_board);
        LinearLayout linearLayoutPhoto=(LinearLayout)findViewById(R.id.liphoto);
        linearLayoutPhoto.setOnClickListener(this);
        LinearLayout linearLayoutRef=(LinearLayout)findViewById(R.id.liReference);
        linearLayoutRef.setOnClickListener(this);
        LinearLayout linearLayoutAct=(LinearLayout)findViewById(R.id.liActivity);
        linearLayoutAct.setOnClickListener(this);
        LinearLayout linearLayoutObj=(LinearLayout)findViewById(R.id.liobjective);
        linearLayoutObj.setOnClickListener(this);
        LinearLayout linearLayoutStr=(LinearLayout)findViewById(R.id.liStrength);
        linearLayoutStr.setOnClickListener(this);
        LinearLayout linearLayoutAward=(LinearLayout)findViewById(R.id.liAwards);
        linearLayoutAward.setOnClickListener(this);
        LinearLayout linearLayoutIndustry=(LinearLayout)findViewById(R.id.liiindustry);
        linearLayoutIndustry.setOnClickListener(this);
        LinearLayout linearLayoutInterest=(LinearLayout)findViewById(R.id.liInterest);
        linearLayoutInterest.setOnClickListener(this);
        LinearLayout linearLayoutSkill=(LinearLayout)findViewById(R.id.liSkill);
        linearLayoutSkill.setOnClickListener(this);
        LinearLayout linearLayoutProject=(LinearLayout)findViewById(R.id.liProject);
        linearLayoutProject.setOnClickListener(this);
        LinearLayout linearLayoutExp=(LinearLayout)findViewById(R.id.liExperience);
        linearLayoutExp.setOnClickListener(this);
        LinearLayout linearLayoutSch=(LinearLayout)findViewById(R.id.liSchool);
        linearLayoutSch.setOnClickListener(this);
        LinearLayout linearLayoutPers=(LinearLayout)findViewById(R.id.liPersonal);
        linearLayoutPers.setOnClickListener(this);
        LinearLayout linearLayoutCont=(LinearLayout)findViewById(R.id.liContact);
        linearLayoutCont.setOnClickListener(this);


        btnGenerateResume=(Button)findViewById(R.id.btngenerateResume);
        btnGenerateResume.setOnClickListener(this);
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.liphoto:
                 Intent i=new Intent(CVDashBoard.this,PhotoSignActivity.class);
                 startActivityForResult(i,Photo_ACTIVITY_REQUEST_CODE);
                 break;
            case R.id.liReference:
                Intent ii=new Intent(CVDashBoard.this,ReferenceActivity.class);
                startActivityForResult(ii,Ref_ACTIVITY_REQUEST_CODE);
                break;
            case R.id.liActivity:
                Intent ia=new Intent(CVDashBoard.this,HobbyADDActivity.class);
                startActivityForResult(ia,ACT_ACTIVITY_REQUEST_CODE);
                break;
            case R.id.liobjective:
                Intent io=new Intent(CVDashBoard.this,ObjectiveActivity.class);
                startActivityForResult(io,Project_ACTIVITY_REQUEST_CODE);
                break;
            case R.id.liStrength:
                Intent is=new Intent(CVDashBoard.this,StrenghtsActivity.class);
                startActivityForResult(is,Str_ACTIVITY_REQUEST_CODE);
                break;
            case R.id.liSkill:
                Intent isk=new Intent(CVDashBoard.this,SkillsActivity.class);
                startActivityForResult(isk,SKILL_ACTIVITY_REQUEST_CODE);
                break;
            case R.id.liAwards:
                Intent iaward=new Intent(CVDashBoard.this,AwardsActivity.class);
                startActivityForResult(iaward,Award_ACTIVITY_REQUEST_CODE);
                break;
            case R.id.liiindustry:
                Intent iind=new Intent(CVDashBoard.this,IndustryExposureActivity.class);
                startActivityForResult(iind,Industry_ACTIVITY_REQUEST_CODE);
                break;
            case R.id.liProject:
                Intent ip=new Intent(CVDashBoard.this,ProjectsActivity.class);
                startActivityForResult(ip,Project_ACTIVITY_REQUEST_CODE);
                break;
            case R.id.liPersonal:
                Intent ipp=new Intent(CVDashBoard.this,PersonalInfoActivity.class);
                startActivityForResult(ipp,PERSONAL_ACTIVITY_REQUEST_CODE);
                break;
            case R.id.liContact:
                Intent ic=new Intent(CVDashBoard.this,ContacsDetailsActivity.class);
                startActivityForResult(ic,CONTACT_ACTIVITY_REQUEST_CODE);
                break;
            case R.id.liInterest:
                Intent iintr=new Intent(CVDashBoard.this,InterestsActivity.class);
                startActivityForResult(iintr,Interest_ACTIVITY_REQUEST_CODE);
                break;
            case R.id.liSchool:
                Intent isch=new Intent(CVDashBoard.this,AcademicDetailsActivity.class);
                startActivityForResult(isch,EDU_ACTIVITY_REQUEST_CODE);
                break;
            case R.id.liExperience:
                Intent iexp=new Intent(CVDashBoard.this,ExperienceActivity.class);
                startActivityForResult(iexp,EXP_ACTIVITY_REQUEST_CODE);
                break;
            case R.id.btngenerateResume:
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
                    if (this.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                            PackageManager.PERMISSION_DENIED) {
                        String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                        requestPermissions(permissions, STORAGE_CODE);
                    } else {
                        savepdf();
                    }
                } else {
                     savepdf();
                }

                break;
default:
     break;

        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // check that it is the SecondActivity with an OK result
        if (requestCode == Photo_ACTIVITY_REQUEST_CODE) {
        if (resultCode == RESULT_OK) { // Activity.RESULT_OK

                // get String data from Intent
                 returnFoto = data.getStringExtra("keyPhoto");
            returnSign = data.getStringExtra("keySign");
                // set text view with string
                Toast.makeText(this,"Saved : "+returnFoto,Toast.LENGTH_SHORT).show();
            }
        }
        else if (requestCode == Ref_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) { // Activity.RESULT_OK

                // get String data from Intent
                 returnStringn = data.getStringExtra("rName");
                 returnString1 = data.getStringExtra("rDesignation");
                 returnString2 = data.getStringExtra("rOrganization");
                 returnString3 = data.getStringExtra("rEmail");
                 returnString4 = data.getStringExtra("rMobile");
                // set text view with string
                Toast.makeText(this,"Saved Reference Details : "+returnStringn+", "+returnString1,Toast.LENGTH_SHORT).show();
            }
        }
        else if (requestCode == Obj_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) { // Activity.RESULT_OK

                // get String data from Intent
                 returnobj = data.getStringExtra("KeyObjective");
                 returndate = data.getStringExtra("KeyDate");
                 returnplace = data.getStringExtra("KeyPlace");
                // set text view with string
                Toast.makeText(this,"Saved objective : "+returnobj,Toast.LENGTH_SHORT).show();
            }
        }
        else if (requestCode == ACT_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) { // Activity.RESULT_OK

                // get String data from Intent
                returnH1 = data.getStringExtra("KeyHobby1");
                returnH2 = data.getStringExtra("KeyHobby2");
                returnH3 = data.getStringExtra("KeyHobby3");
                returnH4 = data.getStringExtra("KeyHobby4");
                returnH5 = data.getStringExtra("KeyHobby5");

                // set text view with string
                Toast.makeText(this,"Saved Hobbies: "+returnH1+", "+returnH2,Toast.LENGTH_SHORT).show();
            }
        }
        else if (requestCode == Str_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) { // Activity.RESULT_OK

                // get String data from Intent
                 returnStrenth1 = data.getStringExtra("KeyStrength1");
                 returnStrenth2 = data.getStringExtra("KeyStrength2");
                 returnStrenth3 = data.getStringExtra("KeyStrength3");
                 returnStrenth4 = data.getStringExtra("KeyStrength4");
                 returnStrenth5 = data.getStringExtra("KeyStrength5");

                // set text view with string
                Toast.makeText(this,"Saved : "+returnStrenth1+", "+returnStrenth2,Toast.LENGTH_SHORT).show();
            }
        }
        else if (requestCode == Award_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) { // Activity.RESULT_OK

                // get String data from Intent
                returnaward1 = data.getStringExtra("Keyaward1");
                returnaward2 = data.getStringExtra("Keyaward2");
                returnaward3 = data.getStringExtra("Keyaward3");
                returnaward4 = data.getStringExtra("Keyaward4");
                returnaward5 = data.getStringExtra("Keyaward5");

                // set text view with string
                Toast.makeText(this,"Saved : "+returnaward1+", "+returnaward2,Toast.LENGTH_SHORT).show();
            }
        }
        else if (requestCode == Industry_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) { // Activity.RESULT_OK

                // get String data from Intent
                 returnIndustry1 = data.getStringExtra("KeyIndustry1");
                 returnIndustry2 = data.getStringExtra("KeyIndustry2");
                 returnIndustry3 = data.getStringExtra("KeyIndustry3");

                // set text view with string
                Toast.makeText(this,"Saved Industries : "+returnIndustry1+", "+returnIndustry2,Toast.LENGTH_SHORT).show();
            }
        }
        else if (requestCode == CONTACT_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) { // Activity.RESULT_OK

                // get String data from Intent
                 returnName = data.getStringExtra("KeyName");
                 returnAdress=data.getStringExtra("KeyAdress");
                 returnEmail=data.getStringExtra("KeyEmail");
                 returnPhone=data.getStringExtra("KeyPhone");


                // set text view with string
                Toast.makeText(this,"Saved Contact Details: "+returnName+ ", "+returnAdress+", "+returnEmail+", "+returnPhone,Toast.LENGTH_SHORT).show();
            }
        }
        else if (requestCode == Interest_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) { // Activity.RESULT_OK

                // get String data from Intent
                returnInterest1 = data.getStringExtra("KeyInterest1");
                returnInterest2 = data.getStringExtra("KeyInterest2");
                returnInterest3 = data.getStringExtra("KeyInterest3");
                returnInterest4 = data.getStringExtra("KeyInterest4");
                returnInterest5 = data.getStringExtra("KeyInterest5");
                returnInterest6 = data.getStringExtra("KeyInterest6");
                returnInterest7 = data.getStringExtra("KeyInterest7");

                // set text view with string
                Toast.makeText(this,"Saved : "+returnInterest1+", "+returnInterest2+", "+returnInterest3,Toast.LENGTH_SHORT).show();
            }
        }
        else if (requestCode == Project_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) { // Activity.RESULT_OK

                // get String data from Intent
                 returntitle = data.getStringExtra("ptitle");
                 returnDescr = data.getStringExtra("pDescription");
                 returnDuration = data.getStringExtra("pDuration");
                returnRole = data.getStringExtra("pRole");
                 returnTeam = data.getStringExtra("pteam");
                // set text view with string
                Toast.makeText(this,"Saved project : "+returntitle+" ,"+returnDescr,Toast.LENGTH_SHORT).show();
            }
        }
        else if (requestCode == SKILL_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) { // Activity.RESULT_OK

                // get String data from Intent
                 returnSkill1 = data.getStringExtra("KeySkill1");
                 returnSkill2 = data.getStringExtra("KeySkill2");
                 returnSkill3 = data.getStringExtra("KeySkill3");
                 returnSkill4 = data.getStringExtra("KeySkill4");
                 returnSkill5 = data.getStringExtra("KeySkill5");
                 returnSkill6 = data.getStringExtra("KeySkill6");
                 returnSkill7 = data.getStringExtra("KeySkill7");

                // set text view with string
                Toast.makeText(this,"Saved : "+returnSkill1+", "+returnSkill2,Toast.LENGTH_SHORT).show();
            }
        }
        else if (requestCode == EXP_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) { // Activity.RESULT_OK

                // get String data from Intent
                 expOrg = data.getStringExtra("expOrg");
                expDesignation = data.getStringExtra("expDesignation");

                 expRole = data.getStringExtra("expRole");

                 expstatus = data.getStringExtra("expEmpStatus");

                // set text view with string
                Toast.makeText(this,"Saved Experience details: "+expOrg+expDesignation+expstatus,Toast.LENGTH_SHORT).show();
            }
        }
        else if (requestCode == EDU_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) { // Activity.RESULT_OK

                // get String data from Intent
                 returnCourse = data.getStringExtra("KeyCourse");
                 returnSchool = data.getStringExtra("KeySchool");
                 returngrade = data.getStringExtra("KeyGrade");
                 returnPassyear = data.getStringExtra("KeyPassYear");
                // set text view with string
                Toast.makeText(this,"Saved Academic details: "+returnCourse,Toast.LENGTH_SHORT).show();
            }
        }
        else if (requestCode == PERSONAL_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) { // Activity.RESULT_OK

                // get String data from Intent
                pi1 = data.getStringExtra("KeyNationality");
                 pi2 = data.getStringExtra("KeyDOB");
                 pi3 = data.getStringExtra("KeyMarital");
                 pi4 = data.getStringExtra("KeyCustom");


                // set text view with string
                Toast.makeText(this,"Saved Personal Info: "+pi1,Toast.LENGTH_SHORT).show();
            }
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void savepdf(){
        Document mDoc = new Document();
        String mFileName=new SimpleDateFormat("yyyyMMdd_HHmmss",
                Locale.getDefault()).format(System.currentTimeMillis());
        String mFilePath= Environment.getExternalStorageDirectory()+"/"+mFileName+".pdf";
        try{
            PdfWriter.getInstance(mDoc,new FileOutputStream(mFilePath));
            mDoc.open();
            //    String mText=editTextpt.getText().toString();
            //  mDoc.addAuthor("zunaira zaman");
            addMetaData(mDoc);

            addImage(mDoc);
            PdfPTable table = new PdfPTable(2);
            mDoc.add(new Paragraph(returnName, FontFactory.getFont(FontFactory.TIMES_BOLD,32,Font.BOLD,BaseColor.DARK_GRAY )));
            mDoc.add(new Paragraph("",FontFactory.getFont(FontFactory.TIMES_BOLD,9,Font.NORMAL,BaseColor.DARK_GRAY)));
            mDoc.add(new Paragraph("",FontFactory.getFont(FontFactory.TIMES_BOLD,9,Font.NORMAL,BaseColor.DARK_GRAY)));
            mDoc.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------"));
            mDoc.add(new Paragraph("CONTACT DETAILS",FontFactory.getFont(FontFactory.TIMES_BOLD,9,Font.BOLD,BaseColor.DARK_GRAY )));
            mDoc.add(new Paragraph(returnEmail,FontFactory.getFont(FontFactory.TIMES_BOLD,7,Font.NORMAL,BaseColor.DARK_GRAY  )));
            mDoc.add(new Paragraph(returnPhone,FontFactory.getFont(FontFactory.TIMES_BOLD,7,Font.NORMAL ,BaseColor.DARK_GRAY )));
            mDoc.add(new Paragraph(returnAdress,FontFactory.getFont(FontFactory.TIMES_BOLD,7,Font.NORMAL,BaseColor.DARK_GRAY  )));
            mDoc.add(new Paragraph(pi1,FontFactory.getFont(FontFactory.TIMES_BOLD,7,Font.NORMAL,BaseColor.DARK_GRAY  )));
            mDoc.add(new Paragraph(pi2,FontFactory.getFont(FontFactory.TIMES_BOLD,7,Font.NORMAL,BaseColor.DARK_GRAY  )));
            mDoc.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------"));
            mDoc.add(new Paragraph("OBJECTIVE",FontFactory.getFont(FontFactory.TIMES_BOLD,9,Font.BOLD,BaseColor.DARK_GRAY  )));
            mDoc.add(new Paragraph(returnobj,FontFactory.getFont(FontFactory.TIMES_BOLD,9,Font.BOLD,BaseColor.DARK_GRAY  )));
            mDoc.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------"));
            mDoc.add(new Paragraph("SKILLS",FontFactory.getFont(FontFactory.TIMES_BOLD,9,Font.BOLD,BaseColor.DARK_GRAY  )));
            table.setHeaderRows(1);
            table.addCell(returnSkill1);
            if(returnSkill2!=null){
                table.addCell(returnSkill2);
            }
            if(returnSkill3!=null){
                table.addCell(returnSkill3);
            }
            if(returnSkill4!=null){
                table.addCell(returnSkill4);
            }
            if(returnSkill5!=null){
                table.addCell(returnSkill5);
            }
            if(returnSkill6!=null){
                table.addCell(returnSkill6);
            }
            if(returnSkill7!=null){
                table.addCell(returnSkill7);
            }
            mDoc.add(table);
            mDoc.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------"));
            mDoc.add(new Paragraph("QUALIFICATIONS",FontFactory.getFont(FontFactory.TIMES_BOLD,9,Font.BOLD,BaseColor.DARK_GRAY  )));
            mDoc.add(new Paragraph(returnSchool+System.lineSeparator()+returnPassyear,FontFactory.getFont(FontFactory.TIMES_BOLD,7,Font.NORMAL,BaseColor.DARK_GRAY  )));
            mDoc.add(new Paragraph("Course : "+returnCourse,FontFactory.getFont(FontFactory.TIMES_BOLD,7,Font.NORMAL,BaseColor.DARK_GRAY  )));
            mDoc.add(new Paragraph("GPA : "+returngrade,FontFactory.getFont(FontFactory.TIMES_BOLD,7,Font.NORMAL,BaseColor.DARK_GRAY  )));
            mDoc.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------"));
            mDoc.add(new Paragraph("WORK EXPERIENCE",FontFactory.getFont(FontFactory.TIMES_BOLD,10,Font.BOLD,BaseColor.DARK_GRAY  )));
            mDoc.add(new Paragraph(expOrg+System.lineSeparator()+expDesignation+System.lineSeparator()+expstatus+System.lineSeparator()+expRole+System.lineSeparator(),FontFactory.getFont(FontFactory.TIMES_BOLD,7,Font.NORMAL,BaseColor.DARK_GRAY)));
            mDoc.add(new Paragraph(expOrg+System.lineSeparator()+expDesignation+System.lineSeparator()+expstatus+System.lineSeparator()+expRole+System.lineSeparator(),FontFactory.getFont(FontFactory.TIMES_BOLD,7,Font.NORMAL,BaseColor.DARK_GRAY)));
            mDoc.add(new Paragraph(expOrg+System.lineSeparator()+expDesignation+System.lineSeparator()+expstatus+System.lineSeparator()+expRole+System.lineSeparator(),FontFactory.getFont(FontFactory.TIMES_BOLD,7,Font.NORMAL,BaseColor.DARK_GRAY)));
            mDoc.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------"));
            mDoc.add(new Paragraph("PROJECTS",FontFactory.getFont(FontFactory.TIMES_BOLD,10,Font.BOLD,BaseColor.DARK_GRAY  )));
            mDoc.add(new Paragraph(returntitle+System.lineSeparator()+returnDuration,FontFactory.getFont(FontFactory.TIMES_BOLD,7,Font.NORMAL,BaseColor.DARK_GRAY)));
            mDoc.add(new Paragraph("Description : "+returnDescr,FontFactory.getFont(FontFactory.TIMES_BOLD,7,Font.NORMAL,BaseColor.DARK_GRAY)));
            mDoc.add(new Paragraph("Role : "+returnRole,FontFactory.getFont(FontFactory.TIMES_BOLD,7,Font.NORMAL,BaseColor.DARK_GRAY)));
            mDoc.add(new Paragraph("Team Size : "+returnTeam,FontFactory.getFont(FontFactory.TIMES_BOLD,7,Font.NORMAL,BaseColor.DARK_GRAY)));
            mDoc.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------"));
            mDoc.add(new Paragraph("INDUSTRY EXPOSURE", FontFactory.getFont(FontFactory.TIMES_BOLD, 9, Font.BOLD, BaseColor.DARK_GRAY)));
            if(returnIndustry1!=null) {
                mDoc.add(new Paragraph("Industry 1: " + returnIndustry1, FontFactory.getFont(FontFactory.TIMES_BOLD, 9, Font.BOLD, BaseColor.DARK_GRAY)));
            }if(returnIndustry2!=null) {
                mDoc.add(new Paragraph("Industry 2: " + returnIndustry2, FontFactory.getFont(FontFactory.TIMES_BOLD, 9, Font.BOLD, BaseColor.DARK_GRAY)));
            }
            if(returnIndustry3!=null) {
                mDoc.add(new Paragraph("Industry 3: " + returnIndustry3, FontFactory.getFont(FontFactory.TIMES_BOLD, 9, Font.BOLD, BaseColor.DARK_GRAY)));
            }
            mDoc.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------"));
            mDoc.add(new Paragraph("AWARDS/CERTIFICATIONS",FontFactory.getFont(FontFactory.TIMES_BOLD,9,Font.BOLD,BaseColor.DARK_GRAY  )));
            List list = new List(true, false, 10);
           if(returnaward1!=null) {
               list.add(new ListItem(returnaward1));
           }if(returnaward2!=null) {
              list.add(new ListItem(returnaward2));
          }
            if(returnaward3!=null) {
                list.add(new ListItem(returnaward3));
            }
            if(returnaward4!=null) {
                list.add(new ListItem(returnaward4));
            }
            if(returnaward5!=null) {
              list.add(new ListItem(returnaward5));
            }
            mDoc.add(list);
            mDoc.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------"));
            mDoc.add(new Paragraph("Interests",FontFactory.getFont(FontFactory.TIMES_BOLD,9,Font.BOLD,BaseColor.DARK_GRAY  )));
            table.setHeaderRows(1);
            if(returnInterest1!=null) {
                table.addCell(returnInterest1);
            }if(returnInterest2!=null){
                table.addCell(returnInterest2);
            }if(returnInterest3!=null){
                table.addCell(returnInterest3);
            }if(returnInterest4!=null){
                table.addCell(returnInterest4);
            }if(returnInterest5!=null){
                table.addCell(returnInterest5);
            }if(returnInterest6!=null){
                table.addCell(returnInterest6);
            }if(returnInterest7!=null){
                table.addCell(returnInterest7);
            }
            mDoc.add(table);
            mDoc.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------"));
            mDoc.add(new Paragraph("STRENGTHS/HOBBIES",FontFactory.getFont(FontFactory.TIMES_BOLD,9,Font.BOLD,BaseColor.DARK_GRAY  )));
            List listStrength = new List(true, false, 10);
            if(returnStrenth1!=null) {
                listStrength.add(new ListItem(returnStrenth1));
            }if(returnStrenth2!=null) {
                listStrength.add(new ListItem(returnStrenth2));
            }
            if(returnStrenth3!=null) {
                listStrength.add(new ListItem(returnStrenth3));
            }
            if(returnStrenth4!=null) {
                listStrength.add(new ListItem(returnStrenth4));
            }
            if(returnStrenth5!=null) {
                listStrength.add(new ListItem(returnStrenth5));
            }
            mDoc.add(listStrength);
            mDoc.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------"));
            mDoc.add(new Paragraph("VOLUNTEER WORK",FontFactory.getFont(FontFactory.TIMES_BOLD,9,Font.BOLD,BaseColor.DARK_GRAY  )));
            List listHobbies = new List(true, false, 10);
            if(returnH1!=null) {
                listHobbies.add(new ListItem(returnH1));
            }if(returnH2!=null) {
                listHobbies.add(new ListItem(returnH2));
            }
            if(returnH3!=null) {
                listHobbies.add(new ListItem(returnH3));
            }
            if(returnH4!=null) {
                listHobbies.add(new ListItem(returnH4));
            }
            if(returnH5!=null) {
                listHobbies.add(new ListItem(returnH5));
            }

            mDoc.add(listHobbies);
            mDoc.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------"));
            mDoc.add(new Paragraph("REFERENCES",FontFactory.getFont(FontFactory.TIMES_BOLD,9,Font.BOLD,BaseColor.DARK_GRAY  )));
            mDoc.add(new Paragraph("Name : "+returnStringn,FontFactory.getFont(FontFactory.TIMES_BOLD,6,Font.NORMAL,BaseColor.DARK_GRAY  )));
            mDoc.add(new Paragraph("Designation : "+returnString1,FontFactory.getFont(FontFactory.TIMES_BOLD,6,Font.NORMAL,BaseColor.DARK_GRAY  )));
            mDoc.add(new Paragraph("Organization : "+returnString2,FontFactory.getFont(FontFactory.TIMES_BOLD,6,Font.NORMAL,BaseColor.DARK_GRAY  )));
            mDoc.add(new Paragraph("Email : "+returnString3,FontFactory.getFont(FontFactory.TIMES_BOLD,6,Font.NORMAL,BaseColor.DARK_GRAY  )));
            mDoc.add(new Paragraph("Mobile : "+returnString4,FontFactory.getFont(FontFactory.TIMES_BOLD,6,Font.NORMAL,BaseColor.DARK_GRAY  )));
            mDoc.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------"));
        if(returnSign!=null) {
            addSignImage(mDoc);
        }
            mDoc.close();

            //  mDoc.add(new Paragraph(mText));
            //  addTitlePage(mDoc);
            // addContent(mDoc);
            mDoc.close();
            Toast.makeText(this,mFileName+".pdf \nis saved to \n"+mFilePath,Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }

    }

    private void addImage(Document document) throws IOException, BadElementException {
        String image_path = returnFoto;
        Bitmap bmp = BitmapFactory.decodeFile(image_path);
//        signImage.setImageBitmap(bitmap);

        Drawable d = this.getResources().getDrawable(R.drawable.zoya);
        BitmapDrawable bitDw = ((BitmapDrawable) d);
       // Bitmap bmp = bitDw.getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        Image img = Image.getInstance(stream.toByteArray());
        // Image img = Image.getInstance("res/drawable/zoya.jpg");
        // Adding image to the document
        try {
            img.setAbsolutePosition(100f, 550f);
            //Scale to new height and new width of image
            img.setAbsolutePosition(473f, 750f);
            img.scaleAbsolute(80f,70f);
            document.add(img);
        } catch (DocumentException e) {
            e.printStackTrace();
        }

    }
    private void addSignImage(Document document) throws IOException, BadElementException {

        String image_path = returnSign;
        Bitmap bmp = BitmapFactory.decodeFile(image_path);
//        signImage.setImageBitmap(bitmap);

        Drawable d = this.getResources().getDrawable(R.drawable.zoya);
        BitmapDrawable bitDw = ((BitmapDrawable) d);
        // Bitmap bmp = bitDw.getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        Image img = Image.getInstance(stream.toByteArray());
        // Image img = Image.getInstance("res/drawable/zoya.jpg");
        // Adding image to the document
        try {
            img.setAbsolutePosition(100f, 550f);
            //Scale to new height and new width of image
            img.setAbsolutePosition(473f, 750f);
            img.scaleAbsolute(100f,250f);
           // img.setFixedPosition(100, 250);
            document.add(img);
        } catch (DocumentException e) {
            e.printStackTrace();
        }

    }

    private static void addMetaData(Document document) {
        document.addTitle("My first PDF");
        document.addSubject("Using iText");
        document.addKeywords("Java, PDF, iText");
        document.addAuthor("Zunaira ");
        document.addCreator("Zaman");
    }
    private static void addTitlePage(Document document)
            throws DocumentException {
        Paragraph preface = new Paragraph();
        // We add one empty line
        addEmptyLine(preface, 1);
        // Lets write a big header
        preface.add(new Paragraph("Title of the document", catFont));

        addEmptyLine(preface, 1);
        // Will create: Report generated by: _name, _date
        preface.add(new Paragraph(
                "Report generated by: " + System.getProperty("user.name") + ", " + new Date(), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                smallBold));
        addEmptyLine(preface, 3);
        preface.add(new Paragraph(
                "This document describes something which is very important ",
                smallBold));

        addEmptyLine(preface, 8);

        preface.add(new Paragraph(
                "This document is a preliminary version and not subject to your license agreement or any other agreement with vogella.com ;-).",
                redFont));

        document.add(preface);
        // Start a new page
        document.newPage();
    }
    private static void addContent(Document document) throws DocumentException {
        Anchor anchor = new Anchor("First Chapter", catFont);
        anchor.setName("First Chapter");

        // Second parameter is the number of the chapter
        Chapter catPart = new Chapter(new Paragraph(anchor), 1);

        Paragraph subPara = new Paragraph("Subcategory 1", subFont);
        Section subCatPart = catPart.addSection(subPara);
        subCatPart.add(new Paragraph("Hello"));

        subPara = new Paragraph("Subcategory 2", subFont);
        subCatPart = catPart.addSection(subPara);
        subCatPart.add(new Paragraph("Paragraph 1"));
        subCatPart.add(new Paragraph("Paragraph 2"));
        subCatPart.add(new Paragraph("Paragraph 3"));

        // add a list
        createList(subCatPart);
        Paragraph paragraph = new Paragraph();
        addEmptyLine(paragraph, 5);
        subCatPart.add(paragraph);

        // add a table
        createTable(subCatPart);

        // now add all this to the document
        document.add(catPart);

        // Next section
        anchor = new Anchor("Second Chapter", catFont);
        anchor.setName("Second Chapter");

        // Second parameter is the number of the chapter
        catPart = new Chapter(new Paragraph(anchor), 1);

        subPara = new Paragraph("Subcategory", subFont);
        subCatPart = catPart.addSection(subPara);
        subCatPart.add(new Paragraph("This is a very important message"));

        // now add all this to the document
        document.add(catPart);

    }

    private static void createTable(Section subCatPart)
            throws BadElementException {
        PdfPTable table = new PdfPTable(3);

        // t.setBorderColor(BaseColor.GRAY);
        // t.setPadding(4);
        // t.setSpacing(4);
        // t.setBorderWidth(1);

        PdfPCell c1 = new PdfPCell(new Phrase("Table Header 1"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Table Header 2"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Table Header 3"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        table.setHeaderRows(1);

        table.addCell("1.0");
        table.addCell("1.1");
        table.addCell("1.2");
        table.addCell("2.1");
        table.addCell("2.2");
        table.addCell("2.3");

        subCatPart.add(table);

    }

    private static void createList(Section subCatPart) {
        List list = new List(true, false, 10);
        list.add(new ListItem("First point"));
        list.add(new ListItem("Second point"));
        list.add(new ListItem("Third point"));
        subCatPart.add(list);
    }

    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }


    public  void onRequestPermissionResult(int requestCode,String[] permissions,int[] grantResults){
        switch (requestCode){
            case STORAGE_CODE:
                if(grantResults.length > 0 && grantResults[0]== PackageManager.PERMISSION_GRANTED ){

                }else{
                    Toast.makeText(this,"Permission denied...",Toast.LENGTH_SHORT).show();
                }
        }
    }

}
