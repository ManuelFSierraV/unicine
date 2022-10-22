insert into Cliente values ("11111","laura@email.com",1,"Laura","111","rutaFoto");
insert into Cliente values ("22222","jose@email.com",1,"Jose","222","rutaFoto");
insert into Cliente values ("33333","carlos@email.com",0,"Carlos","333","rutaFoto");
insert into Cliente values ("44444","pepito@email.com",1,"Pepito","444","rutaFoto");
insert into Cliente values ("55555","juan@email.com",1,"Juan","555","rutaFoto");

insert into Ciudad values (1, "Neiva");
insert into Ciudad values (2, "Cali");
insert into Ciudad values (3, "Armenia");
insert into Ciudad values (4, "Pereira");
insert into Ciudad values (5, "Bogota");

insert into AdministradorTeatro values (1,"admint1@email.com","111");
insert into AdministradorTeatro values (2,"admint2@email.com","222");
insert into AdministradorTeatro values (3,"admint3@email.com","333");
insert into AdministradorTeatro values (4,"admint4@email.com","444");
insert into AdministradorTeatro values (5,"admint5@email.com","555");

insert into Teatro values (1,"calle1","unicine-neiva","8745671",1,1);
insert into Teatro values (2,"calle2","unicine-cali","8745672",2,2);
insert into Teatro values (3,"calle3","unicine-armenia","8745673",3,3);
insert into Teatro values (4,"calle4","unicine-pereira","8745674",4,4);
insert into Teatro values (5,"calle5","unicine-bogota","8745675",5,5);

insert into Distribucion values (1,"10","esquema1","15",130);
insert into Distribucion values (2,"10","esquema2","10",100);
insert into Distribucion values (3,"10","esquema3","12",120);
insert into Distribucion values (4,"10","esquema4","09",100);
insert into Distribucion values (5,"10","esquema5","15",130);

insert into Sala values (1,"sala1",1,1);
insert into Sala values (2,"sala2",2,2);
insert into Sala values (3,"sala3",3,3);
insert into Sala values (4,"sala4",4,4);
insert into Sala values (5,"sala5",5,5);

insert into Horario values (1,"lMV","12-11-2022","12-10-2022");
insert into Horario values (2,"lMV","15-02-2022","15-01-2022");
insert into Horario values (3,"lMV","26-12-2022","26-11-2022");
insert into Horario values (4,"MJS","03-07-2022","03-06-2022");
insert into Horario values (5,"MJS","22-07-2022","22-06-2022");

insert into Pelicula values (1,"cartelera","ciencia-ficcion, drama","Rapidos y furiosos","vin diesel, paul walker","autos,crimen","rutaFoto","rutaTrailer");
insert into Pelicula values (2,"cartelera","ciencia-ficcion, aventura","Animales fantasticos","johnyy depp,eddie redmayne","magia,mitologia","rutaFoto","rutaTrailer");
insert into Pelicula values (3,"estreno","ciencia-ficcion","wakanda for ever","chadwick boseman, letitia wright","secuela de marvel","rutaFoto","rutaTrailer");
insert into Pelicula values (4,"cartelera","ciencia-ficcion, drama","Maze runer","Dylan o´brien, kaya scodelario","ciencia,aventura","rutaFoto","rutaTrailer");
insert into Pelicula values (5,"cartelera","ciencia-ficcion, aventura","Harry potter","Daniel radcliffe, emma stone","magia, mitologia","rutaFoto","rutaTrailer");

insert into Funcion values (1,20000,1,1,1);
insert into Funcion values (2,15000,2,2,2);
insert into Funcion values (3,45000,3,3,3);
insert into Funcion values (4,30000,4,4,4);
insert into Funcion values (5,25000,5,5,5);

insert into Compra values (1,"15-07-22","Nequi",60000,"111",1);
insert into Compra values (2,"16-12-21","TarjetaCredito",80000,"222",2);
insert into Compra values (3,"10-09-22","Nequi",30000,"333",3);
insert into Compra values (4,"19-03-22","TarjetaDebito",60000,"444",4);
insert into Compra values (5,"24-04-22","Efectivo",55000,"555",5);

insert into Cupon values (1,"cupon de descuento","15%","valido","03-06-23");
insert into Cupon values (2,"cupon de descuento","10%","invalido","01-06-21");
insert into Cupon values (3,"cupon de descuento","5%","canjeado","18-09-22");
insert into Cupon values (4,"cupon de descuento","20%","valido","18-11-22");
insert into Cupon values (5,"cupon de descuento","25%","valido","31-07-22");

insert into Confiteria values (1,"palomitas sabor a caramelo grandes","palomitas",15000,"rutaFoto");
insert into Confiteria values (2,"palomitas sabor a caramelo pequeñas","palomitas",7500,"rutaFoto");
insert into Confiteria values (3,"palomitas sabor a mantequilla grandes","palomitas",15000,"rutaFoto");
insert into Confiteria values (4,"palomitas sabor a mantequilla pequeñas","palomitas",7500,"rutaFoto");
insert into Confiteria values (5,"perro caliente personal","hot-dog",9000,"rutaFoto");

insert into CuponCliente values (1,"activo","111",1,1);
insert into CuponCliente values (2,"inactivo","222",2,2);
insert into CuponCliente values (3,"activo","333",3,3);
insert into CuponCliente values (4,"inactivo","444",4,4);
insert into CuponCliente values (5,"activo","555",5,5);

insert into CompraConfiteria values (1,30000,2,1,1);
insert into CompraConfiteria values (2,7500,1,2,2);
insert into CompraConfiteria values (3,30000,2,3,3);
insert into CompraConfiteria values (4,7500,1,4,4);
insert into CompraConfiteria values (5,9000,1,5,5);

insert into Boleta values (1,"15","H",7000,1);
insert into Boleta values (2,"10","C",7500,2);
insert into Boleta values (3,"11","I",8000,3);
insert into Boleta values (4,"06","E",8500,4);
insert into Boleta values (5,"05","F",9000,5);

insert into Solicitud values (1,"Demora","Queja","111");
insert into Solicitud values (2,"Demora","solicitud","222");
insert into Solicitud values (3,"Demora","Reclamo","333");
insert into Solicitud values (4,"Demora","Sugerencia","444");
insert into Solicitud values (5,"Confiteria","Queja","555");

insert into Administrador values (1,"admin1@email.com","111");
insert into Administrador values (2,"admin2@email.com","222");
insert into Administrador values (3,"admin3@email.com","333");
insert into Administrador values (4,"admin4@email.com","444");
insert into Administrador values (5,"admin5@email.com","555");

insert into Telefono values ("3130987654","111");
insert into Telefono values ("3205430966","222");
insert into Telefono values ("3117652345","333");
insert into Telefono values ("3226008008","444");
insert into Telefono values ("3150001122","555");

insert into FechaEspecial values (1,"Reserva para cumpleaños","15-08-2022","111",1);
insert into FechaEspecial values (2,"Reserva para cumpleaños","12-05-2021","222",2);
insert into FechaEspecial values (3,"Reserva para amor y amistad","11-10-2022","111",3);
insert into FechaEspecial values (4,"Reserva para cumpleaños","15-06-2022","333",4);
insert into FechaEspecial values (5,"Reserva para cumpleaños","15-02-2022","555",5);

