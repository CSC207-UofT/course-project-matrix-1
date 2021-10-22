package EquationBuilder;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * @author Will Jeong
 * @version 1.0
 * @since 2021-10-12
 */
public class BedmasEquationDirectorTest {

    @Test
    public void testAddPosAns() {
        BedmasEquationDirector bed = new BedmasEquationDirector();
        BedmasEquationBuilder addBeb = new AddBedmasEquationBuilder();
        bed.setBedmasEquationBuilder(addBeb);
        bed.constructBedmasEquation(0,10,false);
        System.out.println(Arrays.toString(bed.getBedmasEquation().getEquation()));
    }
    @Test
    public void testAddNegAns() {
        BedmasEquationDirector bed = new BedmasEquationDirector();
        BedmasEquationBuilder addBeb = new AddBedmasEquationBuilder();
        bed.setBedmasEquationBuilder(addBeb);
        bed.constructBedmasEquation(0,10,true);
        System.out.println(Arrays.toString(bed.getBedmasEquation().getEquation()));
    }
    @Test
    public void testSubPosAns() {
        BedmasEquationDirector bed = new BedmasEquationDirector();
        BedmasEquationBuilder subBeb = new SubBedmasEquationBuilder();
        bed.setBedmasEquationBuilder(subBeb);
        bed.constructBedmasEquation(0,10,false);
        System.out.println(Arrays.toString(bed.getBedmasEquation().getEquation()));
    }
    @Test
    public void testSubNegAns() {
        BedmasEquationDirector bed = new BedmasEquationDirector();
        BedmasEquationBuilder subBeb = new SubBedmasEquationBuilder();
        bed.setBedmasEquationBuilder(subBeb);
        bed.constructBedmasEquation(0,10,true);
        System.out.println(Arrays.toString(bed.getBedmasEquation().getEquation()));
    }
}