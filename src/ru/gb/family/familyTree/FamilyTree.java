package ru.gb.family.familyTree;




import ru.gb.family.familyTree.ItemFamilyTrees.ItemFamilyTree;
import ru.gb.family.familyTree.ItemFamilyTrees.comparators.ItemFamilyTreeComparatorByAge;
import ru.gb.family.familyTree.ItemFamilyTrees.comparators.ItemFamilyTreeComparatorByBirthday;
import ru.gb.family.familyTree.ItemFamilyTrees.comparators.ItemFamilyTreeComparatorByChildren;
import ru.gb.family.familyTree.ItemFamilyTrees.comparators.ItemFamilyTreeComparatorByName;
import ru.gb.family.familyTree.ItemFamilyTrees.enums.DegreeOfKinship;
import ru.gb.family.familyTree.ItemFamilyTrees.enums.Gender;

import java.io.Serializable;
import java.util.*;

public class FamilyTree<T extends ItemFamilyTree<T>> implements Serializable, Iterable<ItemFamilyTree<T>> {
    private long ItemFamilyTreeId;
    private List<ItemFamilyTree<T>> familyTree;


    public FamilyTree() {
        familyTree = new ArrayList<>();

        }
    public void addItemFamilyTreeInTree(ItemFamilyTree<T> newItemFamilyTree) {
        if (newItemFamilyTree == null) {
            return;
        }
        if (!(familyTree.contains(newItemFamilyTree))) {
            this.familyTree.add(newItemFamilyTree);
            newItemFamilyTree.setId((int) ItemFamilyTreeId++);
        }
    }
    public void sortByAge(){
        familyTree.sort(new ItemFamilyTreeComparatorByAge());
    }
    public void sortByBirthday(){
        familyTree.sort(new ItemFamilyTreeComparatorByBirthday());
    }
    public void sortByChildren(){
        familyTree.sort(new ItemFamilyTreeComparatorByChildren());
    }
    public void sortByName(){
        this.familyTree.sort(new ItemFamilyTreeComparatorByName());
    }

    public List<ItemFamilyTree<T>> searchByItemFamilyTree(ItemFamilyTree<T> searchItemFamilyTree){
        List<ItemFamilyTree<T>> result = new ArrayList<>();
        for (ItemFamilyTree<T> fd : familyTree ) {
            if (fd.equals(searchItemFamilyTree)) {
                result.add(fd);
            }
        }
        return result;
    }
    public List<ItemFamilyTree<T>> searchByName (String name){
        List<ItemFamilyTree<T>> result = new ArrayList<>();
        for (ItemFamilyTree<T> fd : familyTree ){
            if (fd.getName().contains(name)){
                result.add(fd);
            }
        }
        return result;
    }
    public List<ItemFamilyTree<T>> searchByGender (Gender gender){
        List<ItemFamilyTree<T>> result = new ArrayList<>();
        for (ItemFamilyTree<T> fd : familyTree ){
            if (fd.getGender().equals(gender)){
                result.add(fd);
            }
        }
        return result;
    }
    public List<ItemFamilyTree<T>> searchByDegreeOfKinship (DegreeOfKinship degreeOfKinship){
        List<ItemFamilyTree<T>> result = new ArrayList<>();
        for (ItemFamilyTree<T> fd : familyTree ){
            switch (degreeOfKinship) {
                case Mother:
                    if (fd.getMother() != null){
                        result.add(fd);
                    }
                    break;
                case Father:
                    if (fd.getFather() != null){
                        result.add(fd);
                    }
                    break;
                case Children:
                    if (!(fd.getChildren().isEmpty())){
                        result.add(fd);
                    }
                    break;
            }
        }
        return result;
    }

    public StringBuilder findChildrenItemFamilyTree (ItemFamilyTree<T> searchItemFamilyTree){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("-----У человека [").append(searchItemFamilyTree.getName()).append("]  из семейного дерева-------------");
        stringBuilder.append("\n");
        for (ItemFamilyTree<T> fd : this.familyTree ){
           if (fd.equals(searchItemFamilyTree)){
               if (fd.getChildren() != null){
                   stringBuilder.append("Есть дети:\n");

                   // список детей
                   for (ItemFamilyTree<T> itemFamilyTree : fd.getChildren()){
                       stringBuilder.append(itemFamilyTree.getName()+"("+itemFamilyTree.getAge()+" лет.))");
                       stringBuilder.append("\t\n");

                   }

               }
               else{
                   stringBuilder.append("\nНет детей! ");
               }
               stringBuilder.append("----------------------------------------------");

            }
        }
        return stringBuilder;
    }



    @Override
    public Iterator<ItemFamilyTree<T>> iterator() {
        return new ItemFamilyTreeIterator();
    }
    public StringBuilder printResult(List<ItemFamilyTree<T>> result){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
        stringBuilder.append("Результаты поиска:");
        for (ItemFamilyTree<T> itemFamilyTree : result){
            stringBuilder.append("\n id=" + itemFamilyTree.getId() + '\t' +
                    "name=" +itemFamilyTree.getName() +" ("+itemFamilyTree.getAge()+" лет.)"+
                    "\t birthday(" + itemFamilyTree.getBirthday() +
                    ")\t dateOfDeath(" + itemFamilyTree.getDateOfDeath() +
                    ")\t gender=" + itemFamilyTree.getGender());

            stringBuilder.append("\t");
        }
    return stringBuilder;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Список семейного Дерева:\n");
        for (ItemFamilyTree<T> itemFamilyTree : familyTree){
            stringBuilder.append(itemFamilyTree);
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
