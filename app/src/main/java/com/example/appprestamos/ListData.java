package com.example.appprestamos;

public class ListData {
    public String pre_desc, fecha_i, fecha_f, cli_nombre;
    public float pre_monto;
    public int pre_id, image;
    public ListData(int pre_id,String pre_desc,float pre_monto,String fecha_f, String fecha_i) {
        this.pre_id = pre_id;
        this.pre_desc = pre_desc;
        this.pre_monto = pre_monto;
        this.fecha_f = fecha_f;
        this.fecha_i = fecha_i;
    }

    /*public ListData(int pre_id,String pre_desc,float pre_monto, String cli_nombre, String fecha_i,String fecha_f, int image) {
        this.pre_id = pre_id;
        this.pre_desc = pre_desc;
        this.pre_monto = pre_monto;
        this.fecha_i = fecha_i;
        this.cli_nombre = cli_nombre;
        this.fecha_f = fecha_f;
        this.fecha_i = fecha_i;
        this.image = image;
    }*/
}
