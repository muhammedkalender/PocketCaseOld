package software.kalender.pocketcase.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import software.kalender.pocketcase.Singleton;
import software.kalender.pocketcase.chances.ItemQualityChance;
import software.kalender.pocketcase.enums.ColorEnum;

@Entity(tableName = "itemSkins")
public class ItemSkinModel {
    @ColumnInfo(name = "itemSkinId")
    @PrimaryKey(autoGenerate = true)
    public long itemSkinId;

    @NonNull
    @ColumnInfo(name = "itemSkinName")
    public String name;

    @NonNull
    @Embedded
    public ItemModel item;

    @NonNull
    @ColumnInfo(name = "itemSkinColor")
    public ColorEnum color;

    @NonNull
    @Embedded
    public CaseModel skinCase;

    //Default skins/id
    @ColumnInfo(name = "itemSkinImagePath")
    public String itemSkinImagePath;

    @NonNull
    @ColumnInfo(name = "itemSkinQualityChance")
    public ItemQualityChance itemQualityChance;

    @Ignore
    public ItemSkinModel insert() {
        this.itemSkinId = Singleton.db.itemSkinDao().insert(this);

        return this;
    }
}
