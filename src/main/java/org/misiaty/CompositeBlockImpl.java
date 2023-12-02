package org.misiaty;

import java.util.List;

public class CompositeBlockImpl extends BlockImpl implements CompositeBlock  {
    private List<Block> blocks = List.of();
    public CompositeBlockImpl(String color, String material) {
        super(color, material);
    }

    public CompositeBlockImpl(String color, String material, List<Block> blocks) {
        super(color, material);
        this.blocks = blocks;
    }

    public CompositeBlockImpl(List<Block> blocks) {
        super(null, null);
        this.blocks = blocks;
    }

    @Override
    public List<Block> getBlocks() {
        return blocks;
    }
}
