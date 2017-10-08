package sf.codingcomp.blocks.solution;

import java.util.Iterator;

import sf.codingcomp.blocks.BuildingBlock;

public class BuildingBlockImpl implements BuildingBlock {

	private BuildingBlock blockOver = null;
	private BuildingBlock blockUnder = null;
	
    @Override
    public Iterator<BuildingBlock> iterator() {
        Iterator<BuildingBlock> it = new Iterator<BuildingBlock>() {
        	private BuildingBlock currBlock;
        	private BuildingBlock nextBlock;
        	
        	public boolean hasNext() {
        		if (nextBlock != null) return true;
        		else return false;
        	}
        	
        	public BuildingBlock next() {
        		currBlock = nextBlock;
        		nextBlock = currBlock.findBlockOver();
        		return currBlock;
        	}
        	
        	public void remove() {
        		BuildingBlock removeMe = currBlock;
        		BuildingBlock prevBlock = currBlock.findBlockUnder();
        		currBlock = null;
        		removeMe.stackUnder(null);
        		removeMe.stackOver(null);
        		prevBlock.stackUnder(nextBlock);
        		nextBlock.stackOver(prevBlock);
        	}
        };
        return it;
    }

    @Override
    public void stackOver(BuildingBlock b) {
    	 this.blockUnder = b;
        if (b.findBlockOver() != this) {b.stackUnder(this);}
    }

    @Override
    public void stackUnder(BuildingBlock b) {
        this.blockOver = b;
        if (b.findBlockUnder() != this) {b.stackOver(this);}
        
    }

    @Override
    public BuildingBlock findBlockUnder() {
        return this.blockUnder;
    }

    @Override
    public BuildingBlock findBlockOver() {
        return this.blockOver;
    }
}
