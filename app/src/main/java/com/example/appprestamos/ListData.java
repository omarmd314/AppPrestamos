package com.example.appprestamos;

public class ListData {
    String pre_desc, fecha_i, fecha_f, cli_nombre;
    int pre_id, image;
    public ListData(int pre_id,String pre_desc,String cli_nombre, String fecha_i,String fecha_f, int image) {
        this.pre_id = pre_id;
        this.pre_desc = pre_desc;
        this.fecha_i = fecha_i;
        this.cli_nombre = cli_nombre;
        this.fecha_f = fecha_f;
        this.fecha_i = fecha_i;
        this.image = image;
    }
}
