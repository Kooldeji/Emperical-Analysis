# Emperical-Analysis
A Java based graphical analysis of sorting and graph algorithms.

Aimed at analysing and visualising the properties of sorting and graph algorithms.

# Sorting Algorithms
  It analyzes **properties** of pre-inputted sorting algorithms such as 
  * The state of the sequence:
    * Sorted
    * Reverse Sorted
    * Unsorted
  * The maximum peak of the sequence _i.e. The maximum possible element_
  * The size of the sequence _i.e. The no of elements_

  ## Extending
  You can add more algorithms by simply extending the abstract class `EmpricalAnalyser`
  
  `public class CustomSort extends EmpricalAnalyser`
  
  and following its documentation.
    
  The abstract class abstracts the GUI and multithreading **_wahala_**(hassles) from the implementation of the algorithm, given you follow the right steps
    
  To add your algrorithms to the GUI,
  * Create an instance of SortPane 
  
  `SortPane pane = new SortPane(mArray, graphicTg.isSelected());`
  
  where `graphticTg` is the Toggle button that toggles whether to show graphical analysis or not.
  
  * Create an instance of your algorithm and pass in an array and the SortPane instance.
  
  `EmpericalAnalyser algo = new CustomSort(mArray, pane);`
  
  * Simply call the 'addSortOption(String title, EmpericalAnalyser algo, SortPane pane){...}` method
  
  `addSortOption("Custom Sort", algo, pane);`
  
      


