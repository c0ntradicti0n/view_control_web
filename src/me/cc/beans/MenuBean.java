package me.cc.beans;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

@ManagedBean
@ViewScoped
public class MenuBean {
  private MenuModel model = new DefaultMenuModel();

  @PostConstruct
  public void init() {
      addMenu("File", "New", "Open", "Close", "Exit");
      addMenu("Edit", "Undo", "Redo", "Cut", "Copy");
      addMenu(addMenu("View", "Summary"), "Tools",  "Settings", "Layout");
      addMenu("Help", "Help topics", "Support");
  }

  public DefaultSubMenu addMenu(String label, String... items) {
      return addMenu(null, label, items);
  }

  public DefaultSubMenu addMenu(DefaultSubMenu parentMenu,
                                String label, String... items) {
      DefaultSubMenu theMenu = new DefaultSubMenu(label);
      for (Object item : items) {
          DefaultMenuItem mi = new DefaultMenuItem(item);
          mi.setUrl("#");
          theMenu.addElement(mi);
      }
      if (parentMenu == null) {
          model.addElement(theMenu);
      } else {
          parentMenu.addElement(theMenu);
      }
      return theMenu;
  }

  public MenuModel getMenuModel() {
      return model;
  }
}