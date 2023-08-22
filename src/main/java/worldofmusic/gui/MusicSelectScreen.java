package worldofmusic.gui;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
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
import java.util.List;

@Environment(EnvType.CLIENT)
public class MusicSelectScreen extends Screen {
    private final Instrument instrument;

    public MusicSelectScreen(int itemId) {
        super(Text.of("music_select_screen"));
        this.instrument = ((Instrument) Item.byRawId(itemId));
    }

    @Override
    protected void init() {
        super.init();
        List<String> songs = instrument.getSongs(Instrument.PlayCondition.NONE);

        int items = songs.size();
        int width = 155;
        int height = 20;
        int spacing = 5;
        int rows = (int) Math.ceil(items / 3f);
        int menuWidth = (width + spacing) * 3;
        int menuHeight = ((height + spacing) * rows);
        int xPadding = ((this.width-menuWidth)/2) + (spacing/2);
        int x = xPadding;
        int y = ((this.height-menuHeight)/2) + (spacing/2);
        int itemsInRow = 0;

        for(String song : songs) {
            if(rows == 1) {
                if(items != 3) {
                    x = (items == 1) ? (this.width/2 - width/2) : (this.width/2 - width) - spacing/2;
                }
                rows--;
            }

            this.addDrawableChild(new ButtonWidget(x, y, width, height, new TranslatableText("menu." + song), (button -> playSong(song))));
            itemsInRow++;
            items--;

            if(itemsInRow == 3) {
                itemsInRow = 0;
                y += height + spacing;
                x = xPadding;
                rows--;

            } else {
                x += width + spacing;
            }
        }

        this.addDrawableChild(new ButtonWidget(this.width - 55, 5, 50, 20, new TranslatableText("menu.close"), (button -> this.close())));
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);
    }

    private void playSong(String song) {
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeString(song);
        buf.writeString(this.instrument.getInstrumentName());
        ClientPlayNetworking.send(ModPackets.PLAY_SELECTED_SONG_ID, buf);

        this.close();
    }
}
