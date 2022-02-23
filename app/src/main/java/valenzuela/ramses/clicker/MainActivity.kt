package valenzuela.ramses.clicker

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    var cuenta: Int = 0
    var tipo_cuenta: String? = ""
    lateinit var tv_2 : TextView
    lateinit var btn_sumar: Button
    lateinit var tv_cuenta: TextView
    lateinit var edit_tipo: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv_2 = findViewById(R.id.tv_dato)
        btn_sumar = findViewById(R.id.clicker)
        tv_cuenta = findViewById(R.id.textView)
        val btn_restar: Button = findViewById(R.id.btnRestar)
        val btn_borrar: Button = findViewById(R.id.btn_borrar)
        val tv_image: TextView = findViewById(R.id.tv_image)
        edit_tipo = findViewById(R.id.et_tipo)

        btn_sumar.setOnClickListener{
            cuenta++

            if (cuenta == 100){
                tv_image.setText("¿Como llegue aqui?")
            }
            if (cuenta == 110){
                tv_image.setText("")
            }
            if (cuenta == 150){
                tv_image.setText("¿Alguien trae 5 pesos pal camion?...")
            }
            if (cuenta == 165){
                tv_image.setText("")
            }

            tv_cuenta.setText("$cuenta")
        }

        btn_restar.setOnClickListener{
            cuenta--
            tv_cuenta.setText("$cuenta")
        }

        btn_borrar.setOnClickListener {
            borrar()
        }
    }

    fun borrar(){
        val alertDialog: AlertDialog? = this?.let {
            val builder = AlertDialog.Builder(it)
            builder.apply {
                setPositiveButton(R.string.ok,
                    DialogInterface.OnClickListener { dialog, id ->
                        cuenta = 0
                        tv_cuenta.setText("$cuenta")
                    })
                setNegativeButton(R.string.cancel,
                    DialogInterface.OnClickListener { dialog, id ->
                        // User cancelled the dialog
                    })
            }
            // Set other dialog properties
            builder?.setMessage(R.string.dialog_message)
                .setTitle(R.string.dialog_title)
            // Create the AlertDialog
            builder.create()
        }
        alertDialog?.show()
    }

    override fun onStart() {
        super.onStart()
        val preferencias = this.getPreferences(Context.MODE_PRIVATE)
        cuenta = preferencias.getInt("key-cuenta",0)
        tv_cuenta.setText("$cuenta")
        tipo_cuenta = preferencias.getString("key-cuentaTipo","")
        edit_tipo.setText(tipo_cuenta)
    }

    override fun onPostResume() {
        super.onPostResume()

    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
        val preferencias = this.getPreferences(Context.MODE_PRIVATE)
        val editor = preferencias.edit()

        tipo_cuenta = edit_tipo.text.toString()

        editor.putInt("key-cuenta",cuenta)
        editor.putString("key-cuentaTipo",tipo_cuenta)
        editor.apply()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
