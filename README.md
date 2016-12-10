#### Q_CONVERTER - README 

## /SYNOPSIS/
> Simply run and convert!
> QCumver is an easy and free MD(Markdown)-to-HTML converter. All you need to do 
> is to provide your md file name, file style, and the ouptut file name. The ouput HTML 
> file will be viewed directly in your repository.
> The HTML file is qualified by JTidy, and this program is verified sufficiently by 
> JUnit test and JaCoCo. 
> Q_CONVERTER is 100% open source so you can folk the code and contribute! 

## /README/
* Input Format 
> 	in_filename.md  -style  out_filename.html 
> 
> There can be one or more input files. 
> At the end, there should be one output file's name and its type (.html).
> Ignore brackets('[' or ']').
> Separate each element with a white-space.

* Basic Command Line Keywords 
> *File Style*
>	[ -p ] : plain style (default) 
>	[ -f ] : fancy style 
>	[ -s ] : slide style
>
> *Help window*
>	[ -h ]	or [ help ] : "help"; shows help text. 

* More Regulations
 1. Filename only comprises of characters from the ACSII code.
 	It cannot contain any other characters. 
 2. Input and output filename cannot contain any white-space. 
 3. Incoming file and outgoing file's names should be followed
 	by a file type.
 4. User should provide three elements (input_filename, style, and output_filename)
 	in the order suggested.
 5. Multiple input files are separated by a white-space.
 6. If file does not exist, error message comes out. 
 7. These characters are invalid for filename: [ < > : " / \ | ? * ]
 8. These strings are invalid for filename (SYSTEM regulation): [ CON, PRN, AUX, NUL ] 
 9. Only one option for Style is available.


## /CONTRIBUTORS/
* [paulrbear] JooHyung Kim
* [boomkim] BumHwan Kim
* [Younhong] Younhong Ko
* [whaqldutk1] Eunbee Jo


