let buttonUpload = document.getElementById("upload");
let buttonDownload = document.getElementById("download");

console.log(buttonDownload)

buttonUpload.addEventListener("click", uploadFunction)

function uploadFunction(){
    let inputFile = document.getElementById("inputFile");
	let files = inputFile.files;
    let formData = new FormData();
	formData.append("csvFile", files[0]);
    let xhr = new XMLHttpRequest();
	xhr.open("POST", window.location.href + "file/upload", true);
						
	xhr.onreadystatechange = () => {

        if (xhr.readyState == 4) {

            if (xhr.status == 200) {
                showMetrics(JSON.parse(xhr.responseText))
            } else {
                console.log("Não Foi")
            }
        }
	};
						
	xhr.send(formData);	
}

function showMetrics(metrics){
    document.getElementById("quantityWomans").textContent = metrics.quantityWomans
    document.getElementById("quantityMans").textContent = metrics.quantityMans
    document.getElementById("ageTotalAverage").innerHTML = '<b>Média Total de Idade: </b>' + metrics.ageTotalAverage
    document.getElementById("ageMansAverage").innerHTML = '<b>Média de Idade Homens: </b>' + metrics.ageMansAverage
    document.getElementById("ageWomansAverage").innerHTML = '<b>Média de Idade Mulheres: </b>' + metrics.ageWomansAverage

}