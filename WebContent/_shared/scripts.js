
/**
 * Confirm before Client side redirect
 */
function confirmGo(msg,url) {
    if ( confirm(msg) ) {
        window.location = url;
    }
}
/*
	Dynamically Append Error Messages
*/
const appendError = (controlName, innerHTML) => {
	 	
			 var controlElement = document.getElementById(controlName);
			 var span = document.createElement("span");
			 span.innerHTML = innerHTML;
			 controlElement.parentNode.insertBefore( span, controlElement.nextSibling );
 		}
	
/*
	Insert Top Corner Message
*/ 
	 const insertMessage = (Message, ErrBoolean) => {
		 let msgobj = document.createElement("div");
         msgobj.setAttribute("id", "CtlMsg");
         if (!ErrBoolean) msgobj.setAttribute("class", "CtlMsg success"); else msgobj.setAttribute("class", "CtlMsg fail");
         msgobj.innerHTML = Message;
		 document.body.appendChild(msgobj);
		 removeSlowly("CtlMsg");
	 }
        

/*
	Fade Out Animation used mainly for Message
*/
	 const removeSlowly = controlName => {
		 	let obj = document.getElementById(controlName);
		 	obj.style.opacity =  1;
		 	setTimeout(() => {
		 		let fx = setInterval(() => {
		        	if (obj.style.opacity != 0) { 
		        		obj.style.opacity -=  0.1;
    				} else {
	    				clearInterval(fx);
			        	obj.remove();    
			        }
				}, 50);	
			}, 1000);     
	 } 
	 
	  const allItems = [{
    quantity:1,
    multibarcode:true,
    name:"vase",
    itemID:123456833
 },{
    quantity:1,
    multibarcode:true,
    name:"chair",
    itemID:123456833
 },{
    quantity:20,
    multibarcode:false,
    name:"flowers",
    itemID:123456834
 }]


 const loadedItems = [{
    quantity:1,
    multibarcode:true,
    name:"vase",
    itemID:123456833
 },{
    quantity:1,
    multibarcode:true,
    name:"chair",
    itemID:123456833
 },{
    quantity:20,
    multibarcode:false,
    name:"flowers",
    itemID:123456834
 }]
 
 const test = () => {
    e.preventDefault();
 } 


 const listen = (e) => {
    if (e.keyCode == 13) {
         e.preventDefault();
        findbarcode();

    }
} 

const increase = (id) => {
    if(!document.getElementById("action").checked) return; //only increments if loading  
    let input = document.querySelector("#loadItems tbody #qty_" + id);
    if (Number(input.getAttribute("value")) < Number(input.getAttribute("max"))) 
        input.setAttribute("value", Number(input.getAttribute("value")) + 1); 
}

const decrease = (id) => {
    let input = document.querySelector("#loadItems tbody #qty_" + id);
    if (Number(input.getAttribute("value")) > 1)
    input.setAttribute("value", Number(input.getAttribute("value")) - 1); 
}

const deleteRow = (e) => {
    // let scanTables = document.querySelectorAll(".scanTable tbody");
    // let row = document.getElementById("td_" + id);

    // scanTables.forEach(element => {
    //     element.removeChild(row); 
    //     let testRows = element.querySelector("td");
    //     if (!testRows) element.classList.add("hidden");
    // });
    let row = e.target.parentElement.parentElement;
    let tbody = row.parentElement;
    let scanDiv = tbody.parentElement.parentElement;
    console.log(row,tbody,scanDiv)
    
    tbody.removeChild(row);
    if (!tbody.querySelector("td")) scanDiv.classList.add("hidden")
    
    
}

const findbarcode = () => {
    
    let action = document.getElementById("action").checked;
    let itemList;
    let table;
    if (action) {
        //Load Items
        table = document.querySelector("#loadItems tbody");
        itemList = allItems;
    } else {
        table = document.querySelector("#returnItems tbody");
        itemList = loadedItems;
    }

    let lblError = document.getElementById("error");
    let txtbox = document.getElementById("barcode");
    lblError.innerText = "";

    if (!txtbox.value) {
        lblError.innerText = "Barcode Field is empty";
        return;
    }

    table.parentElement.parentElement.classList.remove("hidden");
    itemList.forEach(element => {
        if (element.itemID == txtbox.value.trim()) {
            let checkExist = table.querySelector("#td_" + element.itemID)
            if (checkExist) {
                increase(element.itemID)
                txtbox.focus();

            } else {
                let tblRow = document.createElement("tr");  
                tblRow.setAttribute("id", "td_" + element.itemID )
                    let tdID = document.createElement("td");
                        tdID.innerHTML = element.itemID;
                        let inputID = document.createElement("input");
                        inputID.setAttribute("type", "hidden");
                        inputID.setAttribute("id", "id_"+element.itemID);
                        inputID.setAttribute("name", "barcodes");
                        inputID.setAttribute("value", element.itemID);    
                        tdID.appendChild(inputID)
                    tblRow.appendChild(tdID);
                    
                    let tdName = document.createElement("td")
                        let nameRowColSpan = element.multibarcode?2:1;
                        tdName.innerHTML = element.name;
                        tdName.colSpan = nameRowColSpan
                    tblRow.appendChild(tdName);    
                    
                    let tdQty = document.createElement("td");
                    if (action) { //quantity editor for loading only
                            let btnQtyDec = document.createElement("button");
                            btnQtyDec.setAttribute("type", "button");
                            btnQtyDec.classList.add("qtyBtn")
                            btnQtyDec.setAttribute("onclick", "decrease("+element.itemID+")");
                            btnQtyDec.innerHTML = "<i class=\"fas fa-minus\"></i>"
                        tdQty.appendChild(btnQtyDec)

                            let inputQty = document.createElement("input");
                            inputQty.setAttribute("type", "number");
                            inputQty.setAttribute("min", "1");
                            inputQty.setAttribute("max", element.quantity);
                            inputQty.setAttribute("id", "qty_"+element.itemID);
                            inputQty.setAttribute("name", "qty_"+element.itemID);
                            inputQty.setAttribute("value", 1);    
                        tdQty.appendChild(inputQty)

                            let btnQtyInc = document.createElement("button");
                            btnQtyInc.setAttribute("type", "button");
                            btnQtyInc.classList.add("qtyBtn")
                            btnQtyInc.setAttribute("onclick", "increase("+element.itemID+")");
                            btnQtyInc.innerHTML = "<i class=\"fas fa-plus\"></i>"
                        tdQty.appendChild(btnQtyInc)
                    } else { //
                        let inputQty = document.createElement("span");
                            inputQty.innerText =  element.quantity;
                            inputQty.style.marginRight = "1em"
                        tdQty.appendChild(inputQty)
                    }
                    tblRow.appendChild(tdQty);

                    let tdDelete = document.createElement("td");
                            let btnDelete = document.createElement("button");
                            btnDelete.setAttribute("type", "button");
                            btnDelete.setAttribute("onclick", "deleteRow(event)");
                            btnDelete.innerHTML = "<i class=\"fas fa-trash-alt\"></i>"
                        tdDelete.appendChild(btnDelete)
                    tblRow.appendChild(tdDelete);
                table.appendChild(tblRow);              
            }
            txtbox.value = "";
        }
        txtbox.focus();
    });

    if (txtbox.value) {
        lblError.innerText = "Barcode not found";
        return;
    }

}
	 
	 
