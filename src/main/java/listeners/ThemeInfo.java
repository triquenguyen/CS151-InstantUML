package listeners;

import com.intellij.ide.ui.LafManager;
import com.intellij.ide.ui.LafManagerListener;
import com.intellij.ide.ui.laf.LafManagerImpl;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.util.Disposer;
import com.intellij.ui.ColorUtil;
import com.intellij.util.messages.MessageBusConnection;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class ThemeInfo implements LafManagerListener {

   private static LafManagerImpl lafManager;
   private static MessageBusConnection connection;
   private static String font;
   private static String backGroundColor;
   private static String boxColor;
   private static String textColor;

   private ThemeInfo() {
      connection = ApplicationManager.getApplication().getMessageBus().connect();
   }

   public static String getFont() {
      return font;
   }

   public static String getBackGroundColor() {
      return backGroundColor;
   }

   public static String getBoxColor() {
      return boxColor;
   }

   public static String getTextColor() {
      return textColor;
   }

   @Override
   public void lookAndFeelChanged(@NotNull LafManager source) {
      font = UIManager.getFont("TextField.font").getFontName();
      backGroundColor = ColorUtil.toHex(
              UIManager.getColor("TextPane.background"));
      boxColor = ColorUtil.toHex(
              UIManager.getColor("EditorPane.background"));
      textColor = ColorUtil.toHex(
              UIManager.getColor("EditorPane.foreground"));
   }

}
