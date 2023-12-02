package org.misiaty;

import org.junit.Test;
import org.junit.Before;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class WallTest {
    Wall wall;

    @Before
    public void setUp() {
        List<Block> blockList = List.of(
                new BlockImpl("red", "concrete"),
                new BlockImpl("white", "marble"),
                new CompositeBlockImpl(List.of(
                        new BlockImpl("blue", "concrete"),
                        new BlockImpl("gray", "clay"),
                        new CompositeBlockImpl("yellow", "concrete", List.of(
                                new BlockImpl("black", "stone"),
                                new BlockImpl("green", "concrete")
                        )),
                        new CompositeBlockImpl("aqua", "granite", List.of(
                                new BlockImpl("purple", "granite"),
                                new BlockImpl("violet", "granite")
                        ))
                ))
        );
        wall = new Wall(blockList);
    }

    @Test
    public void testFindBlockByColorFirstLevel() {
        assertTrue(wall.findBlockByColor("red").isPresent());
    }

    @Test
    public void testFindBlockByColorSecondLevel() {
        assertTrue(wall.findBlockByColor("blue").isPresent());
    }

    @Test
    public void testFindBlockByColorThirdLevel() {
        assertTrue(wall.findBlockByColor("black").isPresent());
    }

    @Test
    public void testFindBlocksByMaterialFirstLevel() {
        List<Block> foundBlocks = wall.findBlocksByMaterial("marble");
        assertEquals(1, foundBlocks.size());
        assertEquals("white", foundBlocks.get(0).getColor());
    }

    @Test
    public void testFindBlocksByMaterialSecondLevel() {
        List<Block> foundBlocks = wall.findBlocksByMaterial("clay");
        assertEquals(1, foundBlocks.size());
        assertEquals("gray", foundBlocks.get(0).getColor());
    }

    @Test
    public void testFindBlocksByMaterialThirdLevel() {
        List<Block> foundBlocks = wall.findBlocksByMaterial("stone");
        assertEquals(1, foundBlocks.size());
        assertEquals("black", foundBlocks.get(0).getColor());
    }

    @Test
    public void testFindBlocksByMaterialMultipleLevels() {
        List<Block> foundBlocks = wall.findBlocksByMaterial("concrete");
        assertEquals(4, foundBlocks.size());
    }

    @Test
    public void testCount() {
        assertEquals(11, wall.count());
    }
}