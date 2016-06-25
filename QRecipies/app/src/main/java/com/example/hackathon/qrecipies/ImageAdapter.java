package com.example.hackathon.qrecipies;

        import android.content.Context;
        import android.net.Uri;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.BaseAdapter;
        import android.widget.GridView;
        import android.widget.ImageView;

        import java.io.BufferedReader;
        import java.io.FileNotFoundException;
        import java.io.FileReader;
        import java.io.IOException;
        import java.net.URL;
        import java.util.HashMap;
        import java.util.Map;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    public Map<Integer,String> urilist=new HashMap<Integer, String>();
    public Map<Integer,String> namelist=new HashMap<Integer, String>();
    // Constructor
    public ImageAdapter(Context c) {
        mContext = c;
        try {
            BufferedReader br = new BufferedReader(new FileReader("C:\\Dropbox\\ImageResults.txt"));
            try {
                StringBuilder sb = new StringBuilder();
                String line = br.readLine();
                int i=0;
                while (line != null) {
                    String result[]=line.split(" ");
                    urilist.put(i,result[1]);
                    namelist.put(i++,result[0]);

                }
                String everything = sb.toString();
                br.close();
            } catch (IOException e) {
                System.out.println("IOEXCEPTION" + e.getMessage());
            }
        }catch (FileNotFoundException e)
        {
            System.out.println("FILEEXCEPTION" + e.getMessage());
        }

}

    public int getCount() {
        return urilist.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;

        if (convertView == null) {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(100, 100));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            //imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }
        try {

            // Keep all Images in array
            System.out.println();
            Uri uri = Uri.parse(urilist.get(position));
            imageView.setImageURI(uri);
            return imageView;
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return imageView;
    }

}