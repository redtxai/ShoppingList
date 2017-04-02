package com.redtxai.shoppinglist.DAO;

import android.content.Context;
import android.widget.Toast;

import com.redtxai.shoppinglist.model.Item;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by redtxai on 19/03/2017.
 * redtxai@gmail.com
 */

public class ItemListManager {
    final private static String FILE_NAME = "shoppinglistdatabase.bin";
    private List<Item> itemList = new ArrayList<>();
    private ManageFile manageFile;
    private Context context;
    private Integer idControl = -1;

    public ItemListManager(Context context) throws IOException {
        // this.loadFileData(context);
        this.context = context;
    }

    public void saveItemList() throws IOException {
        StringBuilder str = new StringBuilder("");
        for (Item item : this.itemList) {
            str.append(item.toSaveString());
            str.append("\n");
        }
        this.getManageFile().WriteFile(str.toString());
        this.loadFileData();
    }

    public void saveItemList(List<Item> items) throws IOException {
        this.itemList = items;
        this.saveItemList();
    }

    public List<Item> getItemList() throws IOException {
        this.loadFileData();
        return this.itemList;
    }

    public Integer getIdControl() {
        this.idControl++;
        return this.idControl;
    }

    private ManageFile getManageFile() throws IOException {
        if (this.manageFile == null) {
            this.manageFile = new ManageFile(this.context, FILE_NAME);
        }
        return this.manageFile;
    }

    private void loadFileData() throws IOException {
        String fileData = this.getManageFile().ReadFile();
        if (!fileData.isEmpty()) {
            this.setItemList(fileData);
        }
    }

    private void setItemList(String fileData) {
        String[] fileDataSplit = fileData.split("\n");
        for (int i = 0 ; i <= (fileDataSplit.length-1) ; i++) {
            String[] itemString = fileDataSplit[i].split("~");
            this.itemList.add(new Item(Integer.parseInt(itemString[0])
                                    , itemString[1]
                                    , itemString[2]
                                    , Integer.parseInt(itemString[3])));
        }
        this.idControl = this.itemList.get(this.itemList.size()-1).getId();
    }
}
