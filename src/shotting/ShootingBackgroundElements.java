/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shotting;

import java.util.Iterator;
import shotting.Bakgrouds.Building;
import shotting.Bakgrouds.Clouds;
import shotting.Bakgrouds.Grass;
import shotting.Bakgrouds.Street;

/**
 *
 * @author chg
 */
public class ShootingBackgroundElements {

    private Display display;

    public ShootingBackgroundElements(Display display) {
        this.display = display;
    }

    private void addElements() {
        if (display.getCloudList().size() < 3) {
            display.getCloudList().add(new Clouds(display));
        }
        if (display.getBuildingList().size() < 3) {
            display.getBuildingList().add(new Building(display));
        }
        if (display.getStreetList().size() < 3) {
            display.getStreetList().add(new Street(display));
        }
        if (display.getGrassList().size() < 3) {
            display.getGrassList().add(new Grass(display));
        }
    }

    public void checkElements() {
        addElements();

        Iterator<Clouds> cIt = display.getCloudList().iterator();
        while (cIt.hasNext()) {
            Clouds cl = cIt.next();
            cl.moveClouds();

            if (cl.getPoint().x + cl.getWidth() < 0) {
                cIt.remove();
            }

        }

        Iterator<Building> bIt = display.getBuildingList().iterator();
        while (bIt.hasNext()) {
            Building buil = bIt.next();
            buil.moveBuilding();

            if (buil.getPoint().x + buil.getImgWidth() < 0) {
                bIt.remove();
            }
        }

        Iterator<Street> sIt = display.getStreetList().iterator();
        while (sIt.hasNext()) {
            Street st = sIt.next();
            st.moveStreet();

            if (st.getPoint().x + st.getImgWidth() < 0) {
                sIt.remove();
            }
        }

        Iterator<Grass> gIt = display.getGrassList().iterator();
        while (gIt.hasNext()) {
            Grass gr = gIt.next();
            gr.moveGrass();

            if (gr.getPoint().x + gr.getImgWidth() < 0) {
                gIt.remove();
            }
        }

    }

}
