package de.jeff_media.Gardener;

import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.jetbrains.annotations.Nullable;

public class TreeUtils {

        Main main;

        TreeUtils(Main main) {
            this.main = main;
        }

        /*static TreeType bigToSmallTree(TreeType type) {
            switch (type) {
                case BIG_TREE:
                    return TreeType.TREE;
                case MEGA_REDWOOD:
                    return TreeType.REDWOOD;
                case JUNGLE:
                    return TreeType.SMALL_JUNGLE;
                case DARK_OAK:
                default:
                    return null;
            }
        }*/


        static TreeType getTreeType(Block block, boolean isBigTree) {
            switch (block.getType()) {
                case OAK_SAPLING:
                    return TreeType.TREE;
                case SPRUCE_SAPLING:
                    return isBigTree ? TreeType.MEGA_REDWOOD : TreeType.REDWOOD;
                case JUNGLE_SAPLING:
                    return isBigTree ? TreeType.JUNGLE : TreeType.SMALL_JUNGLE;
                case BIRCH_SAPLING:
                    return TreeType.BIRCH;
                case ACACIA_SAPLING:
                    return TreeType.ACACIA;
                case DARK_OAK_SAPLING:
                    return TreeType.DARK_OAK;
                case RED_MUSHROOM:
                    return TreeType.RED_MUSHROOM;
                case BROWN_MUSHROOM:
                    return TreeType.BROWN_MUSHROOM;
                default:
                    return null;
            }
        }
        
        @Nullable
        private Block[] getBigTreeBlocks(Block block) {
            // Only Jungle, Spruce and Dark Oak can be big trees
            if(block.getType()!= Material.DARK_OAK_SAPLING
                    && block.getType()!=Material.SPRUCE_SAPLING
                    && block.getType()!=Material.JUNGLE_SAPLING) return null;

            Material mat = block.getType();

            BlockFace[][] facesList = {
                    { BlockFace.EAST, BlockFace.NORTH, BlockFace.NORTH_EAST },
                    { BlockFace.EAST, BlockFace.SOUTH, BlockFace.SOUTH_EAST },
                    { BlockFace.WEST, BlockFace.NORTH, BlockFace.NORTH_WEST },
                    { BlockFace.WEST, BlockFace.SOUTH, BlockFace.SOUTH_WEST }
            };

            BlockFace[] matchingFaces = null;

            for (BlockFace[] faces : facesList) {
                boolean isPartOfFourSaplings = true;
                for (BlockFace face : faces) {
                    if (block.getRelative(face).getType() != mat) {
                        isPartOfFourSaplings = false;
                        break;
                    }
                }
                if (isPartOfFourSaplings) {
                    matchingFaces = faces;
                    break;
                }
            }
            if(matchingFaces == null) return null;

            Block[] result = new Block[4];
            result[0] = block;
            for(int i = 1; i<4; i++) {
                result[i] = block.getRelative(matchingFaces[i-1]);
            }
            return result;
        }

        static Material getSapling(TreeType treeType) {
            switch (treeType) {
                case TREE:
                    return Material.OAK_SAPLING;
                case ACACIA:
                    return Material.ACACIA_SAPLING;
                case JUNGLE:
                case SMALL_JUNGLE:
                    return Material.JUNGLE_SAPLING;
                case BIRCH:
                    return Material.BIRCH_SAPLING;
                case BROWN_MUSHROOM:
                    return Material.BROWN_MUSHROOM;
                case RED_MUSHROOM:
                    return Material.RED_MUSHROOM;
                case REDWOOD:
                case MEGA_REDWOOD:
                    return Material.SPRUCE_SAPLING;
                case DARK_OAK:
                    return Material.DARK_OAK_SAPLING;
                default:
                    return null;
            }
        }

        // A big tree has to be spawned at the lowest X and lowest Z coordinates of the 4 saplings
        static private Block getRootOfBigTree(Block[] block) {
            int x = block[0].getX();
            int y = block[0].getY();
            int z = block[0].getZ();

            for(int i = 1; i<4; i++) {
                if(block[i].getX() < x) x = block[i].getX();
                if(block[i].getY() < y) y = block[i].getY();
            }

            return block[0].getWorld().getBlockAt(x, y, z);
        }

        /*void spawnParticles(Player p, Block block) {
            if (main.getConfig().getBoolean("config.GrowingParticle")) {
                p.spawnParticle(Particle.SPELL, block.getLocation(), 20, 1D, 0D, 1D);
            }
        }*/

        void growTree(Block block) {

            boolean isBigTree = (getBigTreeBlocks(block)!=null);
            TreeType type = getTreeType(block, isBigTree);
            if(type==null) return;
            Block[] bigTreeBlocks = getBigTreeBlocks(block);
            if(type==TreeType.DARK_OAK && bigTreeBlocks==null) return;

            if(isBigTree) {
                for(Block b : bigTreeBlocks) {
                    b.setType(Material.AIR);
                }
                block = getRootOfBigTree(bigTreeBlocks);
            } else {
                block.setType(Material.AIR);
            }

            // DARK_OAK can only grow as big tree
            if(!(!isBigTree && type == TreeType.DARK_OAK)) {
                block.getWorld().generateTree(block.getLocation(), type);
            }

            if (block.getType().equals(Material.AIR)) {

                Material material = getSapling(type);

                if (material != null) {
                    if(isBigTree) {
                        for(Block b : bigTreeBlocks) {
                            b.setType(material);
                        }
                    } else {
                        block.setType(material);
                    }
                }
            }
        }

        private Boolean checkForMycelium(Block block)
        {
           return block.getRelative(BlockFace.DOWN).getType() == Material.MYCELIUM;
        }

    }