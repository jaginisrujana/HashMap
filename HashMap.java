import java.util.*;
class KeyValue<T>
{
	T key;
	T value;
	
	public KeyValue(){ }
	public KeyValue(T key,T value)
	{
		this.key=key;
		this.value=value;
	}
}
class HashMap<T>
{
	private int currentsize;
	int maxsize;
	KeyValue<T>[] keyvalue;
	boolean[] bit;
	public HashMap(){ }
	public HashMap(int capacity)
	{
		currentsize = 0;
		maxsize = capacity;
		keyvalue = new KeyValue[maxsize];
		bit = new boolean[maxsize];
		for(int i=0;i<maxsize;i++)
		{
			keyvalue[i]=null;
			bit[i] = true;
		}
	}
	private int hash(T key)
	{
		return key.hashCode()%maxsize;
	}
	int probe(int home,T key)
	{
		int i = home;
		int j=1;
		do
		{
			if(keyvalue[i]==null)
				return i;
			i = (i+j**2)%maxsize;
			j++;
		}while(i!=home);
		return -1;
	}
	public boolean insert(T key,T value)
	{
		int home = hash(key);
		int index = probe(home,key);
		if(index!=-1)
		{
			keyvalue[index] = new KeyValue(key,value);
			bit[index] = false;
			return true;
		}
		else
			return false;
	}
	public KeyValue search(T key)
	{
		int home = hash(key);
		int i = home;
		int j=1;
		do
		{
			if(keyvalue[i]!=null && key.equals(keyvalue[i].key))
				return keyvalue[i];
			i = (i+j**2)%maxsize;
			j++;
		}while(i!=home && !bit[i]);
		return null;
	}
	public boolean delete(T key)
	{
		int home = hash(key);
		int i = home;
		int j=1;
		do
		{
			if(keyvalue[i]!=null && key.equals(keyvalue[i].key))
			{
				keyvalue[i] = null;
				return true;
			}
			i = (i+j**2)%maxsize;
			j++;
		}while(!bit[i] && i!=home);
		return false;
	}
	public KeyValue[] getList()
	{
		return keyvalue;
	}
}
