
# Code Review Defect List
## **Reviewer : Brandon Robles** |  GH Repo : [ser316-spring25A-baroble4](https://github.com/RoblesBrandon1/ser316-spring25A-baroble4/tree/Review)
| **ID#** | **Location** | **Problem Description** File and Line Number | **Problem:** Category | **Problem:** Severity | 
|----------|------------------------|-------------------|----------------------------------------|---------------------------------------------|
|1|Game.Java Line 4|There is no File banner for Game code File|CG 1|LOW|
|2|Game.java Line 13|The Variable for points is not Private|CG 4c|LOW|
|3|Game.java Line 72|This is a duplicate and lazy class as there is another class that already counts the letters and that class can be used instead|CS|LOW|
|4|Game.Java Line 130|Multiple constructors of the same class but produce different outputs but this one does not provide a useful output as it set to blanks.|CS|LOW|
|5|Game.Java Line 186|makeGuess class does not have any implementation and does not allow the program to function as it is supposed to|FD|BR|
|6|Game.Java Line 194|Indentation is not consistent as the previous lines of code had the first bracket on the same line as the class but this one is now on its own line|CG 7b |LOW|
|7|Game.Java Line 16|The identifier is too short and not specific to what is supposed to store|CS|LOW|
|8|Game.Java Line 41|The getName() does not return name rather it returns answer|FD|MJ|

### Category:	**CS – Code Smell defect.** **CG – Violation of a coding guideline. Provide the guideline number.** **FD – Functional defect. Code will not produce the expected result.** **MD – Miscellaneous defect, for all other defects.**
### Severity:       **BR - Blocker, must be fixed asap.** **MJ – Major, of high importance but not a Blocker .** **LOW – Low.**
