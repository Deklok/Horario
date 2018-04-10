# Horario

## Instrucciones de Uso
- Se necesita tener instalado MySQLServer
- Se tiene que correr el script SQL (/src/db/bd_horario.sql) antes de utilizar el programa
- Datos para la base de datos:
	- Usuario: root
	- Pass: 12345

## Requerimientos
- Agregar materias (experiencias educativas) que especifiquen el nombre de la materia, los salones disponibles donde se puede impartir la materia, el maestro y las horas a las que se imparte
- Eliminar materias ya registradas
- Visualizar las materias registradas de forma simple y entendible

## Prototipo inicial
![Prototipo](/documentacion/prototipo)

## Casos de Uso
![DiagramaDeCU](/documentacion/casosdeuso)

## Descripcion de Casos de Uso

# CU1 - Agregar EE
- Descripcion
	- El Usuario agrega una nueva Experiencia Educativa (Materia)

- Autor
	- Daniel Escamilla Cortés

- Precondiciones
	- La EE a agregar no debe estar registrada

- Flujo normal
	1. El Usuario ingresa los datos de la EE (nombre, maestro)
	2. El Usuario ingresa los salones donde se impartira la EE [Incluye al CU3 - Agregar Salon]
	3. El Usuario da click en "Aceptar", de lo contrario da "Cancelar" (FA-1)
	4. El Sistema guarda la EE en la Base de Datos (EX-1)
	5. Fin de CU

- Flujos alternos
	- FA1.- El usuario da click en "Cancelar"
		1. El Sistema cierra la ventana
		2. Fin de CU

- Excepciones
	- EX1.- Error en la conexion a la BD
		1. El Sistema manda un mensaje "Error con la Base de Datos"
		2. Fin de CU

- Postcondiciones
	- El sistema guarda la nueva EE

# CU2 - Eliminar EE
- Descripcion
	- El Usuario elimina una Experiencia Educativa (Materia)

- Autor
	- Daniel Escamilla Cortés

- Precondiciones
	- Debe existir al menos una EE registrada

- Flujo normal
	1. El Usuario elige la EE a eliminar
	2. El Usuario da en el boton "Eliminar EE"
	3. El Sistema muestra un mensaje de confirmacion para eliminar
	4. El Usuario da en "Aceptar", de lo conttrario da "Cancelar" (FA-1)
	5. Si hay CLASES registradas de la EE, el Sistema elimina las (EX-1)
	6. El Sistema elimina la EE de la Base de Datos (EX-1)
	7. Fin de CU

- Flujos alternos
	- FA1.- El Usuario da en "Cancelar"
		1. El Sistema cierra la ventana de confirmacion y cancela la eliminacion
		2. Fin de CU

- Excepciones
	- EX1.- Error en la conexion a la BD
		1. El Sistema manda un mensaje "Error con la Base de Datos"
		2. Fin de CU

- Postcondiciones
	- El Sistema elimina la EE
	- El Sistema elimina las Clases de la EE eliminada

# CU3 - Agregar Salon
- Descripcion
	- El Usuario agrega salon(es) a la EE actual

- Autor
	- Daniel Escamilla Cortés

- Precondiciones
	- Debe estar agregandose una nueva EE

- Flujo normal
	1. El Sistema recupera los SALON disponibles (nombre)
	1. El Usuario elige un SALON para asociar a la EE, de lo contrario agrega una nueva (FA-1)
	2. El Usuario da click en el boton "+""
	3. El Sistema asocia el SALON con la EE (EX-1)
	4. Si el Usuario quiere agregar otro SALON (FA-2)
	5. Fin de CU

- Flujos alternos
	- FA1.- El Usuario agrega un Salon nuevo
		1. El Sistema lee el nuevo SALON a agregar
		2. El Sistema registra el nuevo SALON (EX-1)
		3. Regresa al punto 3 del Flujo Normal

	- FA2.- El Usuario agrega otro Salon a la EE
		1. Regresa al punto 1 del Flujo Normal

- Excepciones
	- EX1.- Error en la conexion a la BD
		1. El Sistema muestra un mensaje "Error al agregar salon"

- Postcondiciones
	- El Sistema asocia el Salon con la EE
	- El Sistema agrega el Salon a la BD en caso de que no exista

# CU4 - Gestionar Clases
- Descripcion
	- El Usuario agrega y elimina Clases

- Autor
	- Daniel Escamilla Cortés

- Precondiciones
	- El Sistema debe tener EE y Salones asociados a la EE registradas

- Flujo normal
	1. El Usuario selecciona la EE de la cual agregar una CLASE
	2. El Usuario selecciona el SALON donde se impartira la CLASE
	3. El Usuario selecciona el dia y hora en la interfaz dando click izquierdo en el cuadro que corresponde
	4. El Sistema guarda la CLASE en la Base de Datos (EX-1)
	5. Si el Usuario quiere eliminar una clase (FA-1)
	6. Si el Usuario quiere agregar otra clase (FA-2)
	7. Fin de CU

- Flujos alternos
	- FA1.- El Usuario da click derecho a un panel con una Clase
		1. El Sistema elimina la CLASE seleccionada (EX-1)
		2. Regresa al punto 5 del Flujo Normal

	- FA2.- El Usuario agrega otra clase
		1. Regresa al punto 1 del Flujo Normal

- Excepciones
	- EX1.- Error en la conexion con la BD
		1. El sistema manda un mensaje "Error en la conexion con la BD"

- Postcondiciones
	- Las Clases nuevas son registradas en la hora y dia correspnodientes
	- Las Clases seleccionadas con click derecho se eliminan

## Diagrama del Dominio
![DiagramaDelDominio](/documentacion/casosdeuso.png)

