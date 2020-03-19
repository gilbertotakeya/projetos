using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;


public class ParametroMySql
{
    public String nomeParametro;
    public object valorParametro;


    public ParametroMySql(String nomParametro, object valorParametro)
    {
        this.nomeParametro = nomParametro;
        this.valorParametro = valorParametro;
    }

    public ParametroMySql()
    {

    }
}

