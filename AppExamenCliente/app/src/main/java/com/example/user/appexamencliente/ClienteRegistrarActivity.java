package com.example.user.appexamencliente;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONObject;

public class ClienteRegistrarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_registrar);
    }
    public void onClickAceptar(View view)
    {
        //Controles
        EditText etPaterno = (EditText) findViewById(R.id.paterno);
        EditText etMaterno = (EditText) findViewById(R.id.materno);
        EditText etNombre = (EditText) findViewById(R.id.nombres);
        EditText etDNI = (EditText) findViewById(R.id.dni);
        EditText etCiudad = (EditText) findViewById(R.id.ciudad);
        EditText etDireccion = (EditText) findViewById(R.id.direccion);
        EditText etTelefono = (EditText) findViewById(R.id.telefono);
        EditText etEmail = (EditText) findViewById(R.id.email);

        TextView tvResultado = (TextView) findViewById(R.id.tvResultado);

        String Paterno = etPaterno.getText().toString();
        String Materno= etMaterno.getText().toString();
        String Nombre= etNombre.getText().toString();
        String DNI= etDNI.getText().toString();
        String Ciudad= etCiudad.getText().toString();
        String Direccion= etDireccion.getText().toString();
        String Telefono= etTelefono.getText().toString();
        String Email= etEmail.getText().toString();

        try{
            //Dato

            String url =  "RegistrarCliente.php?"+
                    "paterno="+Paterno+
                    "&materno="+Materno+
                    "&nombre="+Nombre+
                    "&dni="+DNI+
                    "&ciudad="+Ciudad+
                    "&direccion="+Direccion+
                    "&telefono="+Telefono+
                    "&email="+Email;

            String jsonResult = Util.execJsonGetRequest(url);

            //Procesar el resultado
            JSONObject object = new JSONObject(jsonResult);
            String estado = object.getString("estado");
            String msje = object.getString("message");

            if(estado.equals("0"))
            {
                throw new Exception(msje);
            }
            else
            {
                etPaterno.setText("");
                etMaterno.setText("");
                etNombre.setText("");
                etDNI.setText("");
                etCiudad.setText("");
                etDireccion.setText("");
                etTelefono.setText("");
                etEmail.setText("");

                tvResultado.setText(msje);
            }

        } catch (Exception e)
        {
            tvResultado.setText("AQUI EL ERROR "+e.getMessage());
        }
    }

    public void onClickRetornar(View view) {
        // TODO
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        this.finish();
    }
}
