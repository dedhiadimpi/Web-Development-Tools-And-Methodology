<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <style>
            ul{
                list-style: none;
                padding: 0;
                margin-top: 0;
            }
            .vl{
                border-left: 1px solid black;
                height: 500px;
                float: left;
                width: 1%;
            }
        </style>
         <script type="text/javascript">
            function display(a){
                console.log("inside: "+a);
                if(a == "books"){
                    document.getElementById("books").style.display="block";
                    document.getElementById("music").style.display="none";
                    document.getElementById("computers").style.display="none";
            } else if(a == "music"){
                    document.getElementById("books").style.display="none";
                    document.getElementById("music").style.display="block";
                    document.getElementById("computers").style.display="none";
            }else if(a == "computers"){
                    document.getElementById("books").style.display="none";
                    document.getElementById("music").style.display="none";
                    document.getElementById("computers").style.display="block";
            }
            }
        </script>
    </head>
    <body>
        <div style="float: left; width: 10%; height: 500px;">
            <ul>
                <li><a href="#" onclick="display('books')">Books</a></li><br>
                <li><a href="#" onclick="display('music')">Music</a></li><br>
                <li><a href="#" onclick="display('computers')">Computers</a></li><br>
            </ul>
        </div>
        <div class="vl"></div>
        <div id="books" style="margin-top: 2%;">
            <form action="display" method="GET">
                Shop for Books <br><br>
                <hr style="border: 1px solid black; width: 36.5%; margin: 0"><br>
                <a href="display?category=cart" style="margin-left: 30%;">[View Cart]</a><br><br>
            <input type="checkbox" name="book1" value="Java Servlet Programming">Java Servlet Programming [$29.95] <br><br>
            <input type="checkbox" name="book2" value="Oracle Database Programming">Oracle Database Programming [$48.95]<br><br>
            <input type="checkbox" name="book3" value="System Analysis and Design With UML">System Analysis and Design With UML [$14.95]<br><br>
            <input type="checkbox" name="book4" value="Object Oriented Software Engineering">Object Oriented Software Engineering [$35.99]<br><br>
            <input type="checkbox" name="book5" value="Java Web Services">Java Web Services [$27.99]<br><br>
            <input type="hidden" name="category" value="books">
            <input type="submit" value="Add to Cart"><br><br>
        </form>
        </div>
        
        <div id="music" style="display: none; margin-top: 2%;">
            <form action="display" method="GET">
                Shop for Music <br><br>
                <hr style="border: 1px solid black; width: 36.5%; margin: 0"><br>
                <a href="display?category=cart" style="margin-left: 30%;">[View Cart]</a><br><br>
            <input type="checkbox" name="music1" value="I'm Going to Tell You a Secret by Madoma">I'm Going to Tell You a Secret by Madoma [$26.96] <br><br>
            <input type="checkbox" name="music2" value="Baby One More Time by Britney Spears">Baby One More Time by Britney Spears [$10.95]<br><br>
            <input type="checkbox" name="music3" value="Justified by Justin Timberlake">Justified by Justin Timberlake [$9.97]<br><br>
            <input type="checkbox" name="music4" value="Loose by Nelly Furtado">Loose by Nelly Furtado [$13.98]<br><br>
            <input type="checkbox" name="music5" value="Gold Digger by Kanye West">Gold Digger by Kanye West [$27.99]<br><br>
            <input type="hidden" name="category" value="music">
            <input type="submit" value="Add to Cart"><br><br>
        </form>
        </div>
        
        <div id="computers" style="display: none; margin-top: 2%;">
            <form action="display" method="GET">
                Shop for Computers <br><br>
                <hr style="border: 1px solid black; width: 36.5%; margin: 0"><br>
                <a href="display?category=cart" style="margin-left: 30%;">[View Cart]</a><br><br>
            <input type="checkbox" name="computer1" value="Apple MacBook Pro with 12.3' Display">Apple MacBook Pro with 12.3' Display [$1299.99] <br><br>
            <input type="checkbox" name="computer2" value="Asus Laptop with Centrino 2 Black">Asus Laptop with Centrino 2 Black [$949.95]<br><br>
            <input type="checkbox" name="computer3" value="HP Pavilion Laptop with Centrino 2 DV7">HP Pavilion Laptop with Centrino 2 DV7 [$1199.95]<br><br>
            <input type="checkbox" name="computer4" value="Toshiba Satellite Laptop with Centrino 2 - Copper">Toshiba Satellite Laptop with Centrino 2 - Copper [$899.99]<br><br>
            <input type="checkbox" name="computer5" value="Sony VAIO Laptop with Core 2 Duo Cosmopolitan Pink">Sony VAIO Laptop with Core 2 Duo Cosmopolitan Pink [$779.99]<br><br>
            <input type="hidden" name="category" value="computers">
            <input type="submit" value="Add to Cart"><br><br>
        </form>
        </div>
        <div>
            
<!--            <form action="display" method="GET">
            <input type="hidden" name="category" value="cart">
            <input type="submit" value="View Cart">
            </form>-->
        </div> 
        <script>
            display("<%=request.getParameter("category")%>");
        </script>
    </body>
</html>
