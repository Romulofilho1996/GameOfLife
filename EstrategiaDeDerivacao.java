public abstract class EstrategiaDeDerivacao{
	public abstract void set(Cell cell1[][]);
	public abstract boolean shouldKeepAlive(int i, int j, int w, int h);
	public abstract boolean shouldRevive(int i, int j, int w, int h);
}