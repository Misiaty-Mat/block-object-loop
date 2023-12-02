package org.misiaty;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class Wall implements Structure {
    private final List<Block> blocks;

    public Wall(List<Block> blocks) {
        this.blocks = blocks;
    }

    @Override
    public Optional<Block> findBlockByColor(String color) {
        return retrieveCompositeBlocks()
                .filter(block -> color.equals(block.getColor()))
                .findFirst();
    }

    @Override
    public List<Block> findBlocksByMaterial(String material) {
        return retrieveCompositeBlocks()
                .filter(block -> material.equals(block.getMaterial()))
                .toList();
    }

    @Override
    public int count() {
        return Math.toIntExact(retrieveCompositeBlocks().count());
    }

    private Stream<Block> retrieveCompositeBlocks() {
        return retrieveCompositeBlocks(this.blocks);
    }

    // retrieving blocks from composite blocks recursively to a singular stream
    // also counting composite block as a block within a method
    private Stream<Block> retrieveCompositeBlocks(List<Block> blocks) {
        return blocks.stream()
                .flatMap(block -> {
                    if (block instanceof CompositeBlock) {
                        return Stream.concat(
                                Stream.of(block),
                                retrieveCompositeBlocks(((CompositeBlock) block).getBlocks())
                        );
                    }
                    return Stream.of(block);
                });
    }
}
