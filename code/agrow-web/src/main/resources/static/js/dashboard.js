function gerarPDF() {
	let mesAno = document.getElementById("mesAno").value;

	let image1 = new Image();
	image1.src = document.getElementById("myAreaChart").toDataURL('image/png', 1.0);
	let image2 = new Image();
	image2.src = document.getElementById("myBarChart").toDataURL('image/png', 1.0);
	let image3 = new Image();
	image3.src = document.getElementById("myBarChart2").toDataURL('image/png', 1.0);

	var doc = new jsPDF();
	doc.setFillColor(28, 200, 138);
	doc.setTextColor(255, 255, 255);
	// antes do primeiro grafico
    doc.rect(0, 0, 300, 30, "F");
 	// lateral esquerda
    doc.rect(0, 30, 18, 300, "F");
 	// lateral direita
    doc.rect(195, 30, 18, 300, "F");
 	// antes do segundo grafico
    doc.rect(0, 97, 300, 15, "F");
 	// antes do terceiro grafico
 	// x y w h
    doc.rect(0, 185, 300, 15, "F");
    doc.rect(0, 292, 300, 6, "F");

	doc.text("Agrow Web - Dashboard " + mesAno, 60, 10);
	doc.text("Curva de Lactação - Produção de Leite X Dias Em Lactação", 30, 25);
	doc.addImage(image1, 'PNG', 20, 30, 172.8, 64, 'curva_lactacao', 'NONE', 0);
	doc.text("Conservação do Solo", 80, 106);
	doc.addImage(image2, 'PNG', 20, 115, 172.8, 64, 'solo', 'NONE', 0);
	doc.text("Colheitas - Produção (Kg) por Talhão", 70, 195);
	doc.addImage(image3, 'PNG', 20, 205, 172, 75, 'pie', 'NONE', 0);

	let arrMesAno = mesAno.split("/");
	doc.save('Agrow Web-Dashboard-' + arrMesAno[1] + "-" + arrMesAno[0] + '.pdf');
}

function buscarDadosDashboard(buttonElement) {
	buttonElement.textContent = "Aguarde...";
	buttonElement.disabled = "disabled";
	let mesAno = document.getElementById("mesAno").value;
	if (mesAno == "") {
		window.location.href = "/dashboards/-";
	} else {
		let arrMesAno = mesAno.split("/");
		if (arrMesAno.length > 1) {
			window.location.href = "/dashboards/" + arrMesAno[1] + "-" + arrMesAno[0];
		} else {
			window.location.href = "/dashboards/-";
		}
	}
}
