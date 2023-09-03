package worldofmusic.gui;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.Item;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import worldofmusic.item.Instrument;
import worldofmusic.networking.ModPackets;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Environment(EnvType.CLIENT)
public class MusicSelectScreen extends Screen {
    private final Instrument instrument;
    private int currentPageNumber = 0;
    private final int ELEMENT_WIDTH = 155;
    private final int ELEMENT_HEIGHT = 20;
    private final int ELEMENT_SPACING = 5;
    private final List<List<ButtonWidget>> pages = new ArrayList<>();

    public MusicSelectScreen(int itemId) {
        super(Text.of("music_select_screen"));
        this.instrument = ((Instrument) Item.byRawId(itemId));
    }

    private List<ButtonWidget> createNewPage(int numberOfPages) {
        List<ButtonWidget> page = new ArrayList<>();
        page.add(this.addDrawableChild(new ButtonWidget(this.width - 55, 5, 50, ELEMENT_HEIGHT, new TranslatableText("menu.close"), (button -> this.close()))));
        page.add(this.addDrawableChild(new ButtonWidget((this.width/2) + 1, (this.height - ELEMENT_HEIGHT - 5), 20, ELEMENT_HEIGHT, Text.of(">"), (button -> {if(currentPageNumber < numberOfPages-1) currentPageNumber++;}))));
        page.add(this.addDrawableChild(new ButtonWidget(((this.width/2) - 20) - 1, (this.height - ELEMENT_HEIGHT - 5), 20, ELEMENT_HEIGHT, Text.of("<"), (button -> {if(currentPageNumber != 0) currentPageNumber--;}))));
        pages.add(page);

        return page;
    }

    @Override
    protected void init() {
        List<String> songs = instrument.getSongs(Instrument.PlayCondition.NONE);
        int totalSongs = songs.size();
        int totalPages = (int) Math.ceil(totalSongs / 12f);
        int itemsLeft = (totalPages > 1) ? 12 : totalSongs;
        int rows = (int) Math.ceil(itemsLeft / 3f);
        int menuWidth = (ELEMENT_WIDTH + ELEMENT_SPACING) * 3;
        int menuHeight = (ELEMENT_HEIGHT + ELEMENT_SPACING) * rows;
        int xPadding = ((this.width-menuWidth)/2) + (ELEMENT_SPACING/2);
        int x = xPadding;
        int y = ((this.height-menuHeight)/2) + (ELEMENT_SPACING/2);
        int itemsInRow = 0;

        List<ButtonWidget> currentPage = createNewPage(totalPages);

        for(String song : songs) {
            if(rows == 1) {
                if(itemsLeft != 3) {
                    x = (itemsLeft == 1) ? (this.width/2 - ELEMENT_WIDTH/2) : (this.width/2 - ELEMENT_WIDTH) - ELEMENT_SPACING/2;
                }
                rows--;
            }

            currentPage.add(this.addDrawableChild(new ButtonWidget(x, y, ELEMENT_WIDTH, ELEMENT_HEIGHT, new TranslatableText("menu." + song), (button -> playSong(song)))));
            itemsLeft--;
            itemsInRow++;

            if(itemsInRow == 3) {
                itemsInRow = 0;
                y += ELEMENT_HEIGHT + ELEMENT_SPACING;
                x = xPadding;
                rows--;

                if(itemsLeft == 0) {
                    itemsLeft = (totalPages > 1) ? 12 : totalSongs;
                    rows = (int) Math.ceil(itemsLeft / 3f);
                    y = ((this.height-menuHeight)/2) + (ELEMENT_SPACING/2);
                    currentPage = createNewPage(totalPages);
                }
            } else {
                x += ELEMENT_WIDTH + ELEMENT_SPACING;
            }
        }
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        List<ButtonWidget> page = pages.get(currentPageNumber);
        this.clearChildren();

        for(ButtonWidget widget : page) {
            this.addSelectableChild(widget);
            if(widget.getMessage().getString().equals(">") && currentPageNumber == pages.size()-1) widget.active = false;
            if(widget.getMessage().getString().equals("<") && currentPageNumber == 0) widget.active = false;
            widget.render(matrices, mouseX, mouseY, delta);
        }
    }

    private void playSong(String song) {
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeString(song);
        buf.writeString(this.instrument.getInstrumentName());
        ClientPlayNetworking.send(ModPackets.PLAY_SELECTED_SONG_ID, buf);

        this.close();
    }
}
