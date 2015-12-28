<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@page import="dto.EspecificacionMateriaPrimaDTO"%>
<%@page import="dto.MateriaPrimaDTO"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.List"%>

<input id="idArticulo" name="idArticulo"  type="hidden" value="<%= request.getSession().getAttribute("idArt")%>" ></input>

<% request.getSession().getAttribute("dias");%>
<p><label>Tiempo fabricacion</label><input id="tiempo" name="tiempo" size="4" type="text" value="<%= request.getSession().getAttribute("dias")%>"></input><label>dias</label></p>
<button id="guardar" class="button tiny blue">Guardar</button>
<br/>
<br/>
<table id="especificacionTable">
	<thead>
		<tr>
			<th>Referencia</th>
			<th>Nombre</th>
			<th>Cantidad</th>
			

		</tr>
	</thead>
	<tbody>

		<%
			for (EspecificacionMateriaPrimaDTO e : (Set<EspecificacionMateriaPrimaDTO>) request.getSession()
					.getAttribute("em")) {
				request.getSession().setAttribute("n", e);
		%>

		<tr>
			<td>${n.referencia}</td>
			<td>${n.nombre}</td>
			<td>${n.cantidad}</td>
		</tr>
		<%
			}
		%>
	</tbody>

</table>
<br/>
<br/>
<br/>
		<%if(!((List<MateriaPrimaDTO>)request.getSession().getAttribute("mp")).isEmpty()){ %>
<p><label>Materia prima</label>
<select id="selectMateria">
		
		<%
			for (MateriaPrimaDTO e : (List<MateriaPrimaDTO>) request.getSession()
					.getAttribute("mp")) {
				request.getSession().setAttribute("x", e);
		%>
  <option value=${x.referencia}>${x.nombre}</option>
  		<%
			}
		%>
</select></p>
<p><label>Cantidad</label><input id="cantidad" name="cantidad" size="8" type="text"></input></p>
<button id="agregarMateria" class="button tiny blue">Agregar</button>
<% } %>

