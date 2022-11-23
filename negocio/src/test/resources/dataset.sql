insert into cliente values ("11111","laura@email.com",1,"Laura","111","rutaFoto");
insert into cliente values ("22222","jose@email.com",1,"Jose","222","rutaFoto");
insert into cliente values ("33333","carlos@email.com",0,"Carlos","333","rutaFoto");
insert into cliente values ("44444","pepito@email.com",1,"Pepito","444","rutaFoto");
insert into cliente values ("55555","juan@email.com",1,"Juan","555","rutaFoto");

insert into ciudad values (1, "Neiva");
insert into ciudad values (2, "Cali");
insert into ciudad values (3, "Armenia");
insert into ciudad values (4, "Pereira");
insert into ciudad values (5, "Bogota");

insert into administrador_teatro values (1,"admint1@email.com","111");
insert into administrador_teatro values (2,"admint2@email.com","222");
insert into administrador_teatro values (3,"admint3@email.com","333");
insert into administrador_teatro values (4,"admint4@email.com","444");
insert into administrador_teatro values (5,"admint5@email.com","555");

insert into teatro values (1,"calle1","unicine-neiva","8745671",1,1);
insert into teatro values (2,"calle2","unicine-cali","8745672",2,2);
insert into teatro values (3,"calle3","unicine-armenia","8745673",3,3);
insert into teatro values (4,"calle4","unicine-pereira","8745674",4,4);
insert into teatro values (5,"calle5","unicine-bogota","8745675",5,5);

insert into distribucion values (1,"10","esquema1","15",130);
insert into distribucion values (2,"10","esquema2","10",100);
insert into distribucion values (3,"10","esquema3","12",120);
insert into distribucion values (4,"10","esquema4","09",100);
insert into distribucion values (5,"10","esquema5","15",130);

insert into sala values (1,"sala1",1,1);
insert into sala values (2,"sala2",2,2);
insert into sala values (3,"sala3",3,3);
insert into sala values (4,"sala4",4,4);
insert into sala values (5,"sala5",5,5);

insert into horario values (1,"lMV","2022-11-12","2022-10-12");
insert into horario values (2,"lMV","2022-02-15","2022-01-15");
insert into horario values (3,"lMV","2022-12-26","2022-11-26");
insert into horario values (4,"MJS","2022-07-03","2022-06-03");
insert into horario values (5,"MJS","2022-07-22-07","2022-06-22");

insert into pelicula values (1,"cartelera","Rapidos y furiosos","vin diesel, paul walker","autos,crimen","rutaTrailer");
insert into pelicula values (2,"cartelera","Animales fantasticos","johnyy depp,eddie redmayne","magia,mitologia","rutaTrailer");
insert into pelicula values (3,"proximamente","wakanda for ever","chadwick boseman, letitia wright","secuela de marvel","rutaTrailer");
insert into pelicula values (4,"cartelera","Maze runer","Dylan o´brien, kaya scodelario","ciencia,aventura","rutaTrailer");
insert into pelicula values (5,"cartelera","Harry potter","Daniel radcliffe, emma stone","magia, mitologia","rutaTrailer");

insert into funcion values (1,20000,1,1,1);
insert into funcion values (2,15000,2,2,2);
insert into funcion values (3,45000,3,3,3);
insert into funcion values (4,30000,4,4,4);
insert into funcion values (5,25000,5,5,5);

insert into compra values (1,"2022-07-15","Nequi",60000,"11111",1);
insert into compra values (2,"2021-12-16","TarjetaCredito",80000,"22222",2);
insert into compra values (3,"2022-09-10","Nequi",30000,"33333",3);
insert into compra values (4,"2022-03-19","TarjetaDebito",60000,"44444",4);
insert into compra values (5,"2022-04-24","Efectivo",55000,"55555",5);

insert into cupon values (1,"cupon de descuento",0.15,"valido","20223-06-03");
insert into cupon values (2,"cupon de descuento",0.10,"invalido","2023-06-01");
insert into cupon values (3,"cupon de descuento",0.05,"canjeado","2022-09-18");
insert into cupon values (4,"cupon de descuento",0.20,"valido","2022-11-18");
insert into cupon values (5,"cupon de descuento",0.25,"valido","2022-07-31");

insert into confiteria values (1,"palomitas sabor a caramelo grandes","palomitas",15000,"rutaFoto");
insert into confiteria values (2,"palomitas sabor a caramelo pequeñas","palomitas",7500,"rutaFoto");
insert into confiteria values (3,"palomitas sabor a mantequilla grandes","palomitas",15000,"rutaFoto");
insert into confiteria values (4,"palomitas sabor a mantequilla pequeñas","palomitas",7500,"rutaFoto");
insert into confiteria values (5,"perro caliente personal","hot-dog",9000,"rutaFoto");

insert into cupon_cliente values (1,"activo","11111",1,1);
insert into cupon_cliente values (2,"inactivo","22222",2,2);
insert into cupon_cliente values (3,"activo","33333",3,3);
insert into cupon_cliente values (4,"inactivo","44444",4,4);
insert into cupon_cliente values (5,"activo","55555",5,5);

insert into compra_confiteria values (1,30000,2,1,1);
insert into compra_confiteria values (2,7500,1,2,2);
insert into compra_confiteria values (3,30000,2,3,3);
insert into compra_confiteria values (4,7500,1,4,4);
insert into compra_confiteria values (5,9000,1,5,5);

insert into boleta values (1,"15","H",7000,1);
insert into boleta values (2,"10","C",7500,2);
insert into boleta values (3,"11","I",8000,3);
insert into boleta values (4,"06","E",8500,4);
insert into boleta values (5,"05","F",9000,5);

insert into solicitud values (1,"Demora","Queja","11111");
insert into solicitud values (2,"Demora","solicitud","22222");
insert into solicitud values (3,"Demora","Reclamo","33333");
insert into solicitud values (4,"Demora","Sugerencia","44444");
insert into solicitud values (5,"Confiteria","Queja","55555");

insert into administrador values (1,"admin1@email.com","111");
insert into administrador values (2,"admin2@email.com","222");
insert into administrador values (3,"admin3@email.com","333");
insert into administrador values (4,"admin4@email.com","444");
insert into administrador values (5,"admin5@email.com","555");

insert into telefono values ("3130987654","11111");
insert into telefono values ("3205430966","22222");
insert into telefono values ("3117652345","33333");
insert into telefono values ("3226008008","44444");
insert into telefono values ("3150001122","55555");

insert into fecha_especial values (1,"Reserva para cumpleaños","2022-08-15","11111",2);
insert into fecha_especial values (2,"Reserva para cumpleaños","2021-05-12","22222",3);
insert into fecha_especial values (3,"Reserva para amor y amistad","2022-10-11","33333",4);
insert into fecha_especial values (4,"Reserva para cumpleaños","2022-06-15","44444",5);
insert into fecha_especial values (5,"Reserva para cumpleaños","2022-02-15","55555",1);

