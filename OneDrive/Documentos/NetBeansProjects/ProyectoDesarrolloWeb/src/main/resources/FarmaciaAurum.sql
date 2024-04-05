/*Se crea la base de datos */
drop schema if exists FarmaciaAurum;
drop user if exists usuario_proyecto;
CREATE SCHEMA FarmaciaAurum ;

/*Se crea un usuario para la base de datos llamado "usuario_prueba" y tiene la contraseña "Usuario_Clave."*/
create user 'usuario_proyecto'@'%' identified by 'Proyecto_Clave.';

/*Se asignan los prvilegios sobr ela base de datos TechShop al usuario creado */
grant all privileges on FarmaciaAurum.* to 'usuario_prueba'@'%';
flush privileges;

/* la tabla de categoria contiene categorias de productos*/
create table FarmaciaAurum.categoria (
  id_categoria INT NOT NULL AUTO_INCREMENT,
  descripcion VARCHAR(30) NOT NULL,
  ruta_imagen varchar(1024),
  activo bool,
  PRIMARY KEY (id_categoria))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

create table FarmaciaAurum.consulta (
    id_consulta INT NOT NULL AUTO_INCREMENT,
    titulo VARCHAR(255),
    descripcion TEXT,
    fecha VARCHAR(255) NOT NULL,
    ruta_imagen varchar(1024),
    activo BOOLEAN,
  PRIMARY KEY (id_consulta)
);

create table FarmaciaAurum.cuidado (
    id_cuidado INT NOT NULL AUTO_INCREMENT,
    titulo VARCHAR(255),
    descripcion TEXT,
    ruta_imagen varchar(1024),
    activo BOOLEAN,
  PRIMARY KEY (id_cuidado)
);

create table FarmaciaAurum.cita (
    id_cita INT NOT NULL AUTO_INCREMENT,
    fecha VARCHAR(255) NOT NULL,
    nombre_cliente VARCHAR(100) NOT NULL,
    telefono_cliente VARCHAR(15) NOT NULL,
    comentario TEXT,
    ruta_imagen varchar(1024),
    activo boolean,
  PRIMARY KEY (id_cita)
);

create table FarmaciaAurum.grooming (
    id_grooming INT NOT NULL AUTO_INCREMENT,
    fecha VARCHAR(255) NOT NULL,
    nombre_cliente VARCHAR(100) NOT NULL,
    tipo_corte VARCHAR(100) NOT NULL,
    telefono_cliente VARCHAR(15) NOT NULL,
    comentario TEXT,
    ruta_imagen varchar(1024),
    activo boolean,
  PRIMARY KEY (id_grooming)
);

create table FarmaciaAurum.producto (
  id_producto INT NOT NULL AUTO_INCREMENT,
  id_categoria INT NOT NULL,
  descripcion VARCHAR(30) NOT NULL,  
  detalle VARCHAR(1600) NOT NULL, 
  precio double,
  existencias int,  
  ruta_imagen varchar(1024),
  activo bool,
  PRIMARY KEY (id_producto),
  foreign key fk_producto_caregoria (id_categoria) references categoria(id_categoria)  
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

/*Se crea la tabla de clientes llamada cliente... igual que la clase Cliente */
CREATE TABLE FarmaciaAurum.usuario (
  id_usuario INT NOT NULL AUTO_INCREMENT,
  username varchar(20) NOT NULL,
  password varchar(512) NOT NULL,
  nombre VARCHAR(20) NOT NULL,
  apellidos VARCHAR(30) NOT NULL,
  correo VARCHAR(25) NULL,
  telefono VARCHAR(15) NULL,
  ruta_imagen varchar(1024),
  activo boolean,
  PRIMARY KEY (`id_usuario`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

create table FarmaciaAurum.mascota (
  id_mascota INT NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(50) NOT NULL,
  especie VARCHAR(50) NOT NULL,
  raza VARCHAR(50),
  edad INT,
  alergias VARCHAR(255),
  condiciones_salud VARCHAR(255),
  ruta_imagen VARCHAR(1024),
  activo boolean,
  PRIMARY KEY (id_mascota)
);


create table FarmaciaAurum.factura (
  id_factura INT NOT NULL AUTO_INCREMENT,
  id_usuario INT NOT NULL,
  fecha date,  
  total double,
  estado int,
  PRIMARY KEY (id_factura),
  foreign key fk_factura_usuario (id_usuario) references usuario(id_usuario)  
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

create table FarmaciaAurum.venta (
  id_venta INT NOT NULL AUTO_INCREMENT,
  id_factura INT NOT NULL,
  id_producto INT NOT NULL,
  precio double, 
  cantidad int,
  PRIMARY KEY (id_venta),
  foreign key fk_ventas_factura (id_factura) references factura(id_factura),
  foreign key fk_ventas_producto (id_producto) references producto(id_producto) 
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

/*Se insertan 3 registros en la tabla cliente como ejemplo */
INSERT INTO FarmaciaAurum.usuario (id_usuario, username,password,nombre, apellidos, correo, telefono,ruta_imagen,activo) VALUES 
(1,'juan','$2a$10$P1.w58XvnaYQUQgZUCk4aO/RTRl8EValluCqB3S2VMLTbRt.tlre.','Juan', 'Castro Mora',    'jcastro@gmail.com',    '4556-8978', 'https://upload.wikimedia.org/wikipedia/commons/thumb/2/2a/Juan_Diego_Madrigal.jpg/250px-Juan_Diego_Madrigal.jpg',true),
(2,'rebeca','$2a$10$GkEj.ZzmQa/aEfDmtLIh3udIH5fMphx/35d0EYeqZL5uzgCJ0lQRi','Rebeca',  'Contreras Mora', 'acontreras@gmail.com', '5456-8789','https://upload.wikimedia.org/wikipedia/commons/0/06/Photo_of_Rebeca_Arthur.jpg',true),
(3,'pedro','$2a$10$koGR7eS22Pv5KdaVJKDcge04ZB53iMiw76.UjHPY.XyVYlYqXnPbO','Pedro', 'Mena Loria',     'lmena@gmail.com',      '7898-8936','https://upload.wikimedia.org/wikipedia/commons/thumb/f/fd/Eduardo_de_Pedro_2019.jpg/480px-Eduardo_de_Pedro_2019.jpg?20200109230854',true);

/*Se insertan 4 categorias de productos como ejemplo */
INSERT INTO FarmaciaAurum.categoria (id_categoria,descripcion,ruta_imagen,activo) VALUES 
('1','Perros', 'https://static.fundacion-affinity.org/cdn/farfuture/PVbbIC-0M9y4fPbbCsdvAD8bcjjtbFc0NSP3lRwlWcE/mtime:1643275542/sites/default/files/los-10-sonidos-principales-del-perro.jpg',   true), 
('2','Gatos',  'https://s1.elespanol.com/2023/03/10/curiosidades/mascotas/747436034_231551832_1706x1280.jpg',   true),
('3','Conejos','https://upload.wikimedia.org/wikipedia/commons/thumb/3/37/Oryctolagus_cuniculus_Tasmania_2.jpg/250px-Oryctolagus_cuniculus_Tasmania_2.jpg',true),
('4','Pajaros','https://media.glamour.mx/photos/651f19319eb22a7409fd1f50/16:9/w_1920,c_limit/IMG_9465.jpeg',    false);

/*Se inserta una consulta como ejemplo */
INSERT INTO FarmaciaAurum.consulta(id_consulta,titulo,descripcion,fecha,ruta_imagen,activo) VALUES
('1','Mi perro pone su cabeza en la paredes','Mi perro ha agarrado la extraña costumbre de poner su cabeza contrar una pared y hacer presión, desconozco este comportamiento, espero que me puedan responder el por qué de este comportamiento','2024-04-12 12:00:00','https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png', true);

/*Se inserta un cuidado como ejemplo */
INSERT INTO FarmaciaAurum.cuidado(id_cuidado,titulo,descripcion,ruta_imagen,activo) VALUES
('1','La alimentación adecuada para tus perros','La nutrición es una parte importante para el cuidado y atención de tu mascota. Su alimentación debe ser equilibrada y adaptada según su edad, tamaño, actividad física y condiciones de salud, para que así pueda contar con los nutrientes necesarios.','https://dce-documents.s3.amazonaws.com/s3fs-public/styles/max_2600x2600/public/2023-04/alimentacion-perros.jpg.webp?VersionId=uVD_354bnPHUMgfz.mdMObtu3KZBBfvp&itok=m5_DCEAK', true);

/*Se insertan 2 citas como ejemplo */
INSERT INTO FarmaciaAurum.cita (id_cita,fecha,nombre_cliente,telefono_cliente,comentario,ruta_imagen,activo) VALUES 
('1','2024-04-12','Carlos','5452-9866','Mi animal tiene estreñimiento','https://definicion.de/wp-content/uploads/2013/03/perro-1.jpg', false),
('2','2024-05-04','Maria','2733-3455','Mi gata tiene dificultad para caminar','https://www.clinicas-veterpet.com/wp-content/uploads/2022/11/blog-embarazo-gata.jpg', true);

/*Se inserta1 cita de grooming como ejemplo */
INSERT INTO FarmaciaAurum.grooming (id_grooming,fecha,nombre_cliente,tipo_corte,telefono_cliente,comentario,ruta_imagen,activo) VALUES 
('1','2024-04-12','Carlos','pelo corto','5452-9866','Quiero que mi animal tenga el pelo corto','https://definicion.de/wp-content/uploads/2013/03/perro-1.jpg', true);

/*Se inserta una mascota como ejemplo*/
INSERT INTO FarmaciaAurum.mascota(id_mascota,nombre,especie,raza,edad,alergias,condiciones_salud,ruta_imagen,activo) VALUES
('1','Cerberus','Perro','Pitbull','4','ninguna','buena','https://images.hive.blog/0x0/https://res.cloudinary.com/hpiynhbhq/image/upload/v1519253547/h2s1kr8emvaiwwq9cpap.jpg', true);

/*Se insertan 16 productos por categoria */
INSERT INTO FarmaciaAurum.producto (id_producto,id_categoria,descripcion,detalle,precio,existencias,ruta_imagen,activo) VALUES
(1,1,'Comida para perros','Lorem ipsum dolor sit amet consectetur adipiscing elit iaculis, ullamcorper in fringilla eu cras tempor mi. Luctus blandit sapien mauris vestibulum consequat mattis taciti aliquam ullamcorper, sagittis suscipit etiam urna convallis interdum tempor bibendum, ultricies habitant viverra natoque dictum posuere senectus volutpat. Cum ad vehicula condimentum nunc lacus nec tellus eleifend, a platea curae nullam sollicitudin nibh class cursus taciti, posuere purus inceptos facilisis cubilia suspendisse ut.',23000,5,'https://d1cft8rz0k7w99.cloudfront.net/n/7/e/2/b/7e2b5e383a0836b130000ecf07d12722b2e2d507_Update_8409_01.jpg',true),
(2,1,'Peluche para perros','Quisque in ridiculus scelerisque platea accumsan libero sem vel, mi cras metus cubilia tempor conubia fermentum volutpat gravida, maecenas semper sodales potenti turpis enim dapibus. Volutpat accumsan vivamus dignissim blandit vel eget posuere donec id, tempus sagittis aliquam erat luctus ornare aptent cubilia aliquet proin, ultrices ante pretium gravida sed vitae vestibulum aenean. Eleifend nascetur conubia ornare purus a eget at metus est risus natoque, elementum dis vulputate sociosqu integer ut ad nisl dui molestie.',27000,2,'https://static.miscota.com/media/1/photos/products/166875/oleg-de-peluche-para-perros_1_g.jpeg',true),
(3,1,'Cepillo para perros','Natoque lacinia accumsan hendrerit pretium sociis imperdiet a, nullam ornare erat suspendisse praesent porta, euismod in augue tempus aliquet habitasse. Non accumsan nostra cras vestibulum augue facilisi auctor scelerisque suscipit, iaculis maecenas varius sollicitudin lacus netus et ultricies tincidunt, tortor curabitur tempor diam aliquet dis platea integer. Potenti aliquet erat neque vitae et sociis pretium, viverra euismod vivamus scelerisque metus est feugiat curae, parturient auctor aliquam pharetra nam congue.',24000,5,'https://www.groomingmall.com/2465-large_default/cepillo-para-perros-de-cerdas-separadas.jpg',true),
(4,1,'Casa para perro','Elementum sagittis dictumst leo curabitur porta, pellentesque interdum mauris class augue, penatibus vulputate dignissim lobortis, risus euismod ullamcorper ultrices. Hac suspendisse id odio tempus eleifend a malesuada, conubia gravida turpis auctor eget quam eu, fusce taciti lobortis sem netus cum. Etiam lacinia non nibh taciti vulputate ut nullam, curae mollis penatibus facilisi maecenas urna aptent, metus fusce felis magna ullamcorper aenean.',27600,2,'https://ferreteriavidri.com/images/items/large/122928.jpg?v=20240310',true),
(5,2,'Comida para gatos','Aenean senectus diam vitae curae habitant risus a et netus ante, sociis metus quisque euismod aptent etiam platea fringilla class vestibulum, dis habitasse facilisis fusce varius nam arcu blandit congue. Rutrum placerat congue etiam senectus tincidunt fringilla consequat dignissim sollicitudin, vulputate curae accumsan tempor nunc vel eros mus. Mauris donec urna ante proin duis nullam purus maecenas gravida curae iaculis, tempor quam massa cursus mollis per sodales eros diam leo.',45000,5,'https://walmartcr.vtexassets.com/arquivos/ids/419195-1200-900?v=638246111663530000&width=1200&height=900&aspect=true',true),
(6,2,'Peluche para gatos','Auctor id morbi tempor litora fusce potenti, ornare integer imperdiet turpis accumsan enim, sagittis suscipit purus lacus nunc. Posuere tellus elementum imperdiet sollicitudin consequat torquent urna risus, pulvinar ac per quis egestas tristique ultricies, bibendum dignissim congue eu litora malesuada montes. Nisl arcu mi purus auctor nulla sodales torquent facilisis imperdiet, dignissim bibendum justo dictum in congue integer scelerisque sagittis, accumsan laoreet nam augue felis massa varius nostra.',57000,2,'https://animalear.com/media/9/photos/products/016054/peluche-para-gatos-raton-con-parche_g.jpg',true),
(7,2,'Arena de gatos','Cum placerat etiam lobortis curabitur fames class facilisi hac duis, congue vulputate mus feugiat nostra imperdiet neque vehicula. Mi mollis ridiculus montes aenean sagittis vitae metus, netus massa ligula sociis magnis porttitor, torquent nisl eleifend lobortis dignissim at. Eget nostra tellus sagittis condimentum nec felis curabitur dis ad purus, montes dapibus ullamcorper cras vivamus facilisis nascetur lectus porttitor, dictum vulputate luctus pretium ligula eu posuere rhoncus molestie. Nibh platea odio at mollis est, turpis enim felis pharetra tellus placerat, facilisis praesent massa nulla. Accumsan curabitur cras mus turpis malesuada arcu aptent, volutpat praesent habitant senectus quis mollis sed, viverra nec proin nostra cubilia hendrerit.',25000,5,'https://d1cft8rz0k7w99.cloudfront.net/n/9/2/a/6/92a6f64ccdce7455ef6bb811989119d51da7a49b_Litter_4057_01.jpg',true),
(8,2,'Casa doble para gatos','Purus dictumst scelerisque mollis platea malesuada per vehicula lectus blandit sed, vulputate morbi imperdiet duis dapibus congue class accumsan nullam, ligula eleifend tincidunt urna mi condimentum dis posuere tellus. Sem rutrum erat mauris justo nunc odio condimentum in dictumst, cum porttitor lectus dignissim velit nulla gravida lobortis tempus vehicula, pharetra urna ullamcorper metus semper volutpat proin senectus. Aliquam donec cras ante hendrerit enim vitae nostra consequat scelerisque a habitant dictum congue ornare potenti, sodales velit litora suspendisse nullam neque pellentesque dui etiam platea imperdiet pretium luctus.',27600,2,'https://m.media-amazon.com/images/I/61tJjfnv-LL._AC_SL1500_.jpg',true),
(9,3,'Comida para conejos','Morbi egestas sociis magnis curabitur suscipit nostra blandit magna torquent convallis, enim parturient feugiat fringilla litora aliquam turpis nisl at velit, fames aenean dui viverra arcu habitasse nascetur platea ac. Lectus nibh imperdiet nascetur proin potenti nisl mattis fringilla urna consequat diam, pellentesque vulputate magnis ridiculus dignissim nec venenatis primis ut bibendum, penatibus himenaeos mus sapien magna etiam velit justo vivamus metus. Tellus volutpat hendrerit vehicula lacinia aliquam euismod lectus erat posuere, arcu nec morbi dui placerat quisque semper tempor vulputate est, turpis ac dis nostra congue odio per mattis.',15780,5,'https://www.salitreonline.com/wp-content/uploads/2019/08/28764-850x1029.jpg',true),
(10,3,'Juguete para conejos','Rhoncus ante magna cursus consequat proin senectus ridiculus, varius maecenas tellus justo facilisi ligula eros dapibus, taciti sollicitudin vulputate vivamus lacus fusce. Lacus aptent facilisi urna volutpat vestibulum nunc sociis viverra habitasse egestas, vivamus blandit ultricies neque netus pulvinar elementum ac per iaculis, donec euismod porttitor velit diam ullamcorper congue phasellus nam. Feugiat senectus parturient tristique enim ac integer torquent rutrum imperdiet, nec dapibus nam vestibulum sodales phasellus dis egestas urna, donec interdum id dictumst mollis nostra felis euismod. Ornare proin diam lobortis enim maecenas tempus scelerisque nascetur, id quam magna fames vitae posuere luctus tempor, interdum mattis et ac sapien imperdiet ante.',15000,2,'https://m.media-amazon.com/images/I/81mCkWJUwDS._AC_SL1500_.jpg',true),
(11,3,'Cepillo para conejos','Primis quis sollicitudin ac himenaeos dui metus ridiculus, viverra vitae erat litora mauris eget, ut nisl platea feugiat inceptos cum. Diam vitae sem nulla commodo hendrerit duis dictum, tristique senectus maecenas eu augue dignissim lectus, eros cursus felis ornare nisl primis. Tempor facilisi ad scelerisque himenaeos nec ultrices interdum praesent, tincidunt mauris morbi nostra et parturient vivamus odio viverra, eget eu fermentum conubia vestibulum sagittis feugiat. Nulla mus dis rutrum feugiat imperdiet sociosqu non augue tempor sem, arcu natoque ridiculus odio dapibus quis ligula sagittis dui.',25400,5,'https://t2.ea.ltmcdn.com/es/posts/4/2/4/cepillo_blando_25424_4_600.webp',true),
(12,3,'Jaula para conejos','Risus tristique donec faucibus cursus dictumst vestibulum maecenas, ac scelerisque luctus purus senectus quisque pellentesque, dictum commodo accumsan himenaeos placerat suscipit. Pharetra erat cubilia sapien feugiat aenean molestie vulputate ac, lectus phasellus rutrum pretium interdum a natoque varius parturient, pulvinar condimentum praesent mollis ante nulla inceptos. Curabitur vestibulum malesuada justo non nostra nam donec dictum platea, commodo dictumst natoque bibendum leo nibh cras habitant primis, quisque augue eget ultrices pulvinar sodales odio rhoncus. Diam condimentum id pellentesque imperdiet porttitor vestibulum himenaeos iaculis, natoque ornare scelerisque nam nostra taciti tortor malesuada, sapien lacus cubilia suspendisse eros rutrum conubia.',45000,3,'https://static.miscota.com/media/1/photos/products/115985/115985-captura_4_g.JPG',true),
(13,4,'Cómida para pajaros','Nam ad hac curae mollis dui scelerisque convallis eros, dignissim faucibus velit nostra dapibus cursus vehicula habitasse facilisi, mi conubia pellentesque quisque cras justo inceptos. Integer varius consequat volutpat at dui scelerisque dapibus magnis platea quis, purus mi nibh tempor inceptos litora hac himenaeos ultrices. Convallis orci dictumst tincidunt phasellus facilisis ullamcorper montes vestibulum, leo cubilia tempus fringilla sodales per lacus viverra pretium, potenti id sociis fames curae nam etiam.',285000,0,'https://static.miscota.com/media/1/photos/products/135626/nature-four-seasons-comida-para-pajaros_1_g.jpeg',true),
(14,4,'Set de juguetes para pájaros','Luctus lacus montes vulputate libero purus est litora, risus magnis quisque ac urna magna sollicitudin, suspendisse mauris massa euismod quam placerat. Facilisis congue id posuere tortor et porttitor curabitur pulvinar sapien, cubilia tempus pharetra facilisi fringilla dapibus lectus non hendrerit, pellentesque dictumst vulputate natoque molestie aptent nascetur ante. Laoreet etiam taciti integer at metus facilisis, pellentesque tortor leo enim felis turpis bibendum, neque curabitur himenaeos hac torquent.',154000,0,'https://http2.mlstatic.com/D_NQ_NP_767264-MLM72131488870_102023-O.webp',true),
(15,4,'Jaula para pajaros pequeña','Nullam porttitor vivamus phasellus tempus in morbi aliquet platea duis, nulla tristique inceptos pellentesque pulvinar congue sagittis euismod vitae lacinia, scelerisque mus orci sociosqu libero proin sed felis. Pretium tincidunt ultrices eu vel nam massa morbi diam sem, neque aliquet vehicula penatibus odio phasellus curabitur. Conubia natoque quis tellus scelerisque sociis facilisi nisi suspendisse, id interdum ornare vivamus proin himenaeos class sed in, suscipit torquent est aliquam orci nunc etiam. Congue et nisl magna cum id sociis enim suscipit integer, nisi egestas est porttitor sollicitudin commodo natoque pharetra torquent, aliquam euismod nam porta rhoncus non ante habitasse.',330000,0,'https://piximascotas.es/1267-thickbox_default/jaula-mini-51-blanca-295x375x18-cm.jpg',true),
(16,4,'Jaula para pajaros grande','Litora metus senectus mattis egestas mus fames tempus suscipit, inceptos luctus hendrerit congue quis sem. Potenti quis conubia fermentum non dictum nibh, viverra neque sed pretium eros aptent, metus hac at imperdiet est. Accumsan donec sociosqu etiam venenatis felis aenean suspendisse facilisi dignissim conubia non, molestie est ultrices neque id diam pellentesque quis quisque in odio, per nulla aptent arcu vehicula lobortis aliquet tempor cum platea.',273000,0,'https://i.ebayimg.com/images/g/S~MAAOSwJU9lat5l/s-l1600.jpg',true);

/*Se crean 6 facturas */   /*'Activa','Pagada','Anulada')*/
INSERT INTO FarmaciaAurum.factura (id_factura,id_usuario,fecha,total,estado) VALUES
(1,1,'2022-01-05',211560,2),
(2,2,'2022-01-07',554340,2),
(3,3,'2022-01-07',871000,2),
(4,1,'2022-01-15',244140,1),
(5,2,'2022-01-17',414800,1),
(6,3,'2022-01-21',420000,1);

INSERT INTO FarmaciaAurum.venta (id_venta,id_factura,id_producto,precio,cantidad) values
(1,1,5,45000,3),
(2,1,9,15780,2),
(3,1,10,15000,3),
(4,2,5,45000,1),
(5,2,14,154000,3),
(6,2,9,15780,3),
(7,3,14,154000,1),
(8,3,6,57000,1),
(9,3,15,330000,2),
(10,1,6,57000,2),
(11,1,8,27600,3),
(12,1,9,15780,3),
(13,2,8,27600,3),
(14,2,14,154000,2),
(15,2,3,24000,1),
(16,3,15,330000,1),
(17,3,12,45000,1),
(18,3,10,15000,3);

create table FarmaciaAurum.rol (
  id_rol INT NOT NULL AUTO_INCREMENT,
  nombre varchar(20),
  id_usuario int,
  PRIMARY KEY (id_rol),
  foreign key fk_rol_usuario (id_usuario) references usuario(id_usuario)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

insert into FarmaciaAurum.rol (id_rol, nombre, id_usuario) values
 (1,'ROLE_ADMIN',1), (2,'ROLE_VENDEDOR',1), (3,'ROLE_USER',1),
 (4,'ROLE_VENDEDOR',2), (5,'ROLE_USER',2),
 (6,'ROLE_USER',3);