var express =require("express");
var cors = require("cors");
var corsOptions = {origin:"*",optionSucessStatus:200};
var app = express();
var bodyParser = require("body-parser");
app.use(bodyParser.urlencoded({extended:false}));
app.use(bodyParser.json());

app.use(cors(corsOptions));

var personas= [
    {   nombre:"pedro",
        apellido:"Picapiedra",
        telefono:"4666-6666",
        img:"http://www.lslutnfra.com/alumnos/practicas/pedro_pic.jpg"
    },
	{nombre:"Pablo",apellido:"Marmol",telefono:"4777-6666",img:"http://www.lslutnfra.com/alumnos/practicas/pablo_marmol.jpg"},
	{nombre:"Marty",apellido:"McFly",telefono:"4666-6666",img:"http://www.lslutnfra.com/alumnos/practicas/marty_mcfly.jpg"},
    {nombre:"Marcelo",apellido:"Tinelli",telefono:"4666-6666",img:"http://www.lslutnfra.com/alumnos/practicas/marcelo_tinelli.jpg"}
];




app.get("/personas",function(req,res){
    
    res.send(personas);
});


app.post("/nuevaPersona",function(req,res){
    setTimeout(function(){
        console.log("Llego al servidor "+JSON.stringify(req.body));
        console.log((req.body.nombre!= undefined &&req.body.nombre!= "") );
       
        if((req.body.nombre!= undefined&&req.body.nombre!= "") &&(req.body.apellido!= undefined&&req.body.apellido!= "") &&  (req.body.telefono!= undefined&&req.body.telefono!= "") && (req.body.img!= undefined&&req.body.img!= "")){
            
                console.log("Sale del servidor "+"{'respuesta': 'ok'}");
                personas.push(req.body);

                res.send({'respuesta': 'ok'});    
            
            return;
        }
        console.log("Sale del servidor "+"{'respuesta': 'error'}")
        res.send({'respuesta': 'error'});
    },2000);
    
});



app.listen(3000,function(){
    console.log("Api en el puerto 3000");
});