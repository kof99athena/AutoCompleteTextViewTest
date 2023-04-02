package com.athena.autocompletetextviewtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.athena.autocompletetextviewtest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    lateinit var firstFragment: FirstFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 뷰바인딩한 뒤에 반드시 root를 가져와서 작업해야한다.
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)


        val til = binding.textInput  //TextInputLayout을 잡고있는녀석
        var s = til.editText?.text.toString() //TextInputLayout에서 잡고있는 녀석을 글씨로가져온다.

        val acTv = binding.confirmSelect //AutoCompleteTextView를 잡고있는 녀석
        var sort : Array<String> = resources.getStringArray(R.array.sort) //array에 등록된 결재종류들
        var adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,sort)
        acTv.setAdapter(adapter)
        //Array 아탑더에게 simple_list_item_1로 sort에 있는 글자들을 한줄씩 보이게 한다.
        //simple_list_item_1는 안드로이드에 이미 준비가 되어있는 레이아웃이다.
        // simple_list_item_1 : 텍스트뷰 하나로 구성된 레이아웃

        firstFragment = FirstFragment() //첫번째 프래그먼트 객체를 만들어준다.


        binding.confirmSelect.setOnItemClickListener { parent, view, position, id ->
            var s : String = binding.confirmSelect.text.toString()
            //아이템 하나를 눌렀을 때 해당하는 글씨를 가져온다.

            when{
                //그리고 when문으로 해당 글씨에 해당되면 프래그먼트로 넘긴다.
                s.equals("First")->{
                    supportFragmentManager.beginTransaction().replace(R.id.framelayout,firstFragment).commit()
                    true
                }

                s.equals("Second")->{
                    Toast.makeText(this, "두번째 아이템 선택했습니다.", Toast.LENGTH_SHORT).show()
                }

                s.equals("Third")->{
                    AlertDialog.Builder(this).setTitle("세번째").setMessage("다이얼로그 출력").show()
                }

                else -> false
            }

        }//onItemCLickListener

    }
}