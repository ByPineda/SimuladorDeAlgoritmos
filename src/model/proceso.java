package model;

public class proceso {
    private String id;
    private int tiempoLLegada, rafaga, prioridad, tiempoArranque, tiempoFinalizacion, tiempoRetorno,tasaDesperdicio, tiempoRespuesta, tiempoEspera;
    private float tasaPenalizacion;
    /*
    0. Tiempo de llegada           "TLl"
    1. Rafaga de CPU               "t"
    2. Tiempo de Arranque          "TA"
    3. Tiempo de Finalización      "TF"
    Derivados--------------------------------------
    4. Tiempo de Retorno           "TRet = TF - TA"
    5. Tiempo de respuesta         "T = TF - TLl"
    6. Tasa de desperdicio         "W = T - t"
    7. Tasa de penalización        "P = T / t"
    8. Tiempo de espera            "TE = TA - TLl"
    */

    // Constructor por defecto
    public proceso(String id, int tiempoLLegada, int rafaga) {
        this.id = id;
        this.tiempoLLegada = tiempoLLegada;
        this.rafaga = rafaga;
    }

    //Contrsuctor para proceso con prioridad
    public proceso(String id, int tiempoLLegada, int rafaga, int prioridad) {
        this.id = id;
        this.tiempoLLegada = tiempoLLegada;
        this.rafaga = rafaga;
        this.prioridad = prioridad;
    }
    
    // Getters
    public String getId() {
        return id;
    }
    public int getTiempoLLegada() {
        return tiempoLLegada;
    }
    public int getRafaga() {
        return rafaga;
    }
    public int getPrioridad() {
        return prioridad;
    }
    public int getTiempoArranque() {
        return tiempoArranque;
    }
    public int getTiempoFinalizacion() {
        return tiempoFinalizacion;
    }
    public int getTiempoRetorno() {
        return tiempoRetorno;
    }
    public int getTasaDesperdicio() {
        return tasaDesperdicio;
    }
    public int getTiempoRespuesta() {
        return tiempoRespuesta;
    }
    public int getTiempoEspera() {
        return tiempoEspera;
    }
    public float getTasaPenalizacion() {
        return tasaPenalizacion;
    }
    // Setters

    public void setTiempoLLegada(int tiempoLLegada) {
        this.tiempoLLegada = tiempoLLegada;
    }
    public void setRafaga(int rafaga) {
        this.rafaga = rafaga;
    }

    //Auto Caulculados
    public void setParametros() {
        //"TRet = TF - TA"
        this.tiempoRetorno = this.tiempoFinalizacion - this.tiempoArranque;
        //"W = T - t"
        this.tasaDesperdicio = this.tiempoRetorno - this.rafaga;
        //"T = TF - TLl"
        this.tiempoRespuesta = this.tiempoFinalizacion - this.tiempoLLegada;
        //"TE = TA - TLl"
        this.tiempoEspera = this.tiempoArranque - this.tiempoLLegada;
        //"P = T / t"
        this.tasaPenalizacion = (float)this.tiempoRetorno / (float)this.rafaga;
    }


}
