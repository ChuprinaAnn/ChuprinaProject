package problem;

import java.util.ArrayList;

public class jarvisMethod {
        private ArrayList<Vector> v = new ArrayList<Vector>();
        private int n;
        private int h;

        public ArrayList <Vector> computeHull(ArrayList<Vector> v)
        {
            this.v=v;
            n=v.size();
            ArrayList <Vector> k = new ArrayList();
            jarvisMethod();
            return k;
        }

        private void jarvisMethod()
        {
            int i=indexOfLowestPoint();
            ArrayList<Vector> polygonpoints = new ArrayList<>();
            polygonpoints.add(0,v.get(i));
            do
            {
                exchange(h, i);
                i=indexOfRightmostPointFrom(v.get(h));
                polygonpoints.add(v.get(i));
                h++;
            }
            while (i>0);
        }

        private int indexOfLowestPoint()
        {
            int i, min=0;
            for (i=1; i<n; i++)
                if (v.get(i).y<v.get(min).y || v.get(i).y==v.get(min).y && v.get(i).x<v.get(min).x)
                    min=i;
            return min;
        }

        private int indexOfRightmostPointFrom(Vector q)
        {
            int i=0, j;
            for (j=1; j<n; j++)
                if (v.get(j).difference(q).isLess(v.get(i).difference(q)))
                    i=j;
            return i;
        }

        private void exchange(int i, int j)
        {
            Vector t = v.get(i);
            v.set(i,v.get(j));
            v.set(j,t);
        }

}

