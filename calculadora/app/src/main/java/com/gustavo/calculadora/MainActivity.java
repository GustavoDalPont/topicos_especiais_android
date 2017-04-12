package com.gustavo.calculadora;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private EditText etOperando1;
    private EditText etOperando2;
    private RadioGroup rgOperacao;
    private String operacao;
    private TextView tvResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.etOperando1 = (EditText) findViewById(R.id.etOperando1);
        this.etOperando2 = (EditText) findViewById(R.id.etOperando2);
        this.rgOperacao = (RadioGroup) findViewById(R.id.rgOperacao);
        this.tvResultado = (TextView) findViewById(R.id.tvResultado);
    }

    public void calcularResultado(View v) {

        this.operacao = operacaoAtual();
        if (!dadosValidos()) {
            return;
        }

        double operando1 = Double.parseDouble(this.etOperando1.getText().toString());
        double operando2 = Double.parseDouble(this.etOperando2.getText().toString());
        double resultado;
        switch (this.operacao) {
            case "soma":
                resultado = operando1 + operando2;
                this.tvResultado.setText(getResources().getString(R.string.resultado_final, resultado));
                break;
            case "subtracao":
                resultado = operando1 - operando2;
                this.tvResultado.setText(getResources().getString(R.string.resultado_final, resultado));
                break;
            case "multiplicacao":
                resultado = operando1 * operando2;
                this.tvResultado.setText(getResources().getString(R.string.resultado_final, resultado));
                break;
            case "divisao":
                resultado = operando1 / operando2;
                this.tvResultado.setText(getResources().getString(R.string.resultado_final, resultado));
                break;
            default:
                this.tvResultado.setText(getResources().getString(R.string.impossivel_calcular));
                break;
        }
    }

    private boolean dadosValidos() {

        double operando1;
        double operando2;

        this.etOperando1.setError(null);
        this.etOperando2.setError(null);

        try {
            operando1 = Double.parseDouble(this.etOperando1.getText().toString());
        } catch (Exception e) {
            this.etOperando1.setError(getResources().getString(R.string.falha_conversao));
            return false;
        }

        try {
            operando2 = Double.parseDouble(this.etOperando2.getText().toString());
        } catch (Exception e) {
            this.etOperando2.setError(getResources().getString(R.string.falha_conversao));
            return false;
        }

        if (operando2 == 0 && Objects.equals(this.operacao, "divisao")) {
            this.etOperando2.setError(getResources().getString(R.string.dividir_zero));
            this.tvResultado.setText("");
            return false;
        }

        if (Objects.equals(this.operacao, "indefinido")) {
            this.tvResultado.setText(getResources().getString(R.string.seleciona_operacao));
            return false;
        }

        return true;
    }

    private String operacaoAtual() {
        if (this.rgOperacao.getCheckedRadioButtonId() == R.id.rbSoma) {
            return "soma";
        }

        if (this.rgOperacao.getCheckedRadioButtonId() == R.id.rbSubtracao) {
            return "subtracao";
        }

        if (this.rgOperacao.getCheckedRadioButtonId() == R.id.rbMultiplicacao) {
            return "multiplicacao";
        }

        if (this.rgOperacao.getCheckedRadioButtonId() == R.id.rbDivisao) {
            return "divisao";
        }

        return "indefinido";
    }
}
