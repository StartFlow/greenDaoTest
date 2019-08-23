package com.example.demo.practice;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demo.practice.application.MyApplication;
import com.example.demo.practice.entity.DaoSession;
import com.example.demo.practice.entity.Person;
import com.example.demo.practice.entity.PersonDao;
import com.google.android.flexbox.FlexboxLayout;

import org.greenrobot.greendao.query.QueryBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GreenDaoActivity  extends AppCompatActivity{
    final String TAG = "GreenDao";
    private DaoSession personDao;

    @BindView(R.id.id_condition)
    EditText idEd;

    @BindView(R.id.name_condition)
    EditText nameEd;

    @BindView(R.id.height_condition)
    EditText heightEd;

    @BindView(R.id.age_condition)
    EditText ageEd;

    @BindView(R.id.group)
    RadioGroup group;

    @BindView(R.id.insert)
    Button insertBt;

    @BindView(R.id.query)
    Button queryBt;

    @BindView(R.id.show)
    FlexboxLayout boxLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greendao_layout);
        personDao = MyApplication.application.getDaoSession();
        ButterKnife.bind(this);
    }


    @OnClick(R.id.insert)
    public void insertData(){
        try {
            Person person = new Person();
            person.setId(idEd.getText()==null?0:Long.valueOf(idEd.getText().toString()));
            person.setName(nameEd.getText()==null?"":nameEd.getText().toString());
            person.setHeight(heightEd.getText()==null?0:Integer.valueOf(heightEd.getText().toString()));
            person.setAge(ageEd.getText()==null?0:Integer.valueOf(ageEd.getText().toString()));
            switch (group.getCheckedRadioButtonId()){
                case R.id.male:
                    person.setSexy(Person.Sexy_male);
                    break;
                case R.id.female:
                    person.setSexy(Person.Sexy_female);
                    break;
                default:
            }
            personDao.getPersonDao().insertOrReplace(person);
            Toast.makeText(this, "插入成功", Toast.LENGTH_SHORT).show();
            idEd.setText("");
            nameEd.setText("");
            heightEd.setText("");
            ageEd.setText("");
            group.clearCheck();
        }catch (Exception e){
            Toast.makeText(this, "插入失败", Toast.LENGTH_SHORT).show();
        }

    }

    @OnClick(R.id.query)
    public void queryData(){
        QueryBuilder<Person> qb = personDao.queryBuilder(Person.class);
        QueryBuilder<Person> dbresults = qb.where(PersonDao.Properties.Id.eq(idEd.getText()==null?0:Long.valueOf(idEd.getText().toString())));
        Person results = dbresults.unique();
        if (results!=null){
            addView2Group(results);
        }else {
            Toast.makeText(this, "没有查询到对象", Toast.LENGTH_SHORT).show();
        }

    }

    private void addView2Group(Person person){
        TextView view = new TextView(this);
        view.setText(person.toString());
        view.setTextColor(Color.BLUE);
        view.setTextSize(24);
        view.setBackgroundColor(Color.GRAY);
        FlexboxLayout.LayoutParams lp = new FlexboxLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.setMargins(10,10,10,10);
        boxLayout.addView(view,lp);
    }

}
