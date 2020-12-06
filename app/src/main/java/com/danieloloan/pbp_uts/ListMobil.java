package com.danieloloan.pbp_uts;

import java.util.ArrayList;

public class ListMobil {
    public ArrayList<Mobil> MOBIL;

    public ListMobil(){
        MOBIL = new ArrayList();
        MOBIL.add(Avanza);
        MOBIL.add(Xpander);
        MOBIL.add(Fortuner);
        MOBIL.add(Ertiga);
        MOBIL.add(HRV);
        MOBIL.add(CRV);
        MOBIL.add(Calya);
        MOBIL.add(Pajero);
        MOBIL.add(Outlander);
    }

    public static final Mobil Avanza = new Mobil("Toyota", "Avanza",
            "Fuel", 4, 120000,
            "https://img.favpng.com/10/14/3/toyota-avanza-car-bandung-daihatsu-xenia-png-favpng-f2G0vSxhmiLjpEinTkCmYThm7.jpg");

    public static final Mobil Xpander = new Mobil("Mitsubishi", "Xpander",
            "Fuel", 8, 280000,
            "https://img.favpng.com/2/18/14/mitsubishi-xpander-mitsubishi-motors-car-mitsubishi-pajero-png-favpng-STGsPh32WNviDiJztksbVxW0Y.jpg");

    public static final Mobil Fortuner = new Mobil("Toyota", "Fortuner",
            "Solar", 8, 250000,
            "https://www.vhv.rs/dpng/d/106-1067634_toyota-fortuner-png-transparent-png.png");

    public static final Mobil Ertiga = new Mobil("Suzuki", "Ertiga",
            "Fuel", 5, 150000,
            "https://www.pngfind.com/pngs/m/620-6206784_maruti-suzuki-ertiga-car-hd-png-download.png");

    public static final Mobil HRV = new Mobil("Honda", "HRV",
            "Fuel", 8, 200000,
            "https://www.pngfind.com/pngs/m/224-2249528_2019-hr-v-lx-awd-honda-hrv-lx.png");

    public static final Mobil CRV = new Mobil("Honda", "CRV",
            "Fuel", 8, 220000,
            "https://www.vhv.rs/dpng/d/97-976227_white-2016-honda-cr-v-hd-png-download.png");

    public static final Mobil Calya = new Mobil("Toyota", "Calya",
            "Fuel", 8, 150000,
            "https://img.favpng.com/12/8/12/daihatsu-sigra-toyota-calya-1-2-g-m-t-car-rush-png-favpng-nGtbwJdVt07JM2dPG5VcdRcW7.jpg");

    public static final Mobil Pajero = new Mobil("Mitsubishi", "Pajero",
            "Fuel", 8, 280000,
            "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQnyqMmv5umPLNbApxVefHz81GCN7FplJa3FQ&usqp=CAU");

    public static final Mobil Outlander = new Mobil("Mitsubishi", "Outlander",
            "Solar", 8, 275000,
            "https://banner2.cleanpng.com/20180629/iqa/kisspng-2017-mitsubishi-outlander-2018-mitsubishi-outlande-outlander-5b36e9bdb3d1d1.3159845615303254377366.jpg");

}
