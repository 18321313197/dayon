function initDlRoot(root, image1, image2, image3, image4, image5) {
	var nodes = root.childNodes;
	for (var i = 0; i < nodes.length; i++) {
		if (nodes[i].nodeName == "DD") {
			var items = nodes[i].childNodes;
			for (var j = 0; j < items.length; j++) {
				if (items[j].nodeName == "DIV") {
					var cns = items[j].childNodes;
					var img1 = null, img2 = null;
					for (var k = 0; k < cns.length; k++) {
						if (img1 == null) {
							if (cns[k].nodeName == "IMG") {
								img1 = cns[k];
							}
						} else if (img2 == null) {
							if (cns[k].nodeName == "IMG") {
								img2 = cns[k];
							}
						} else {
							break;
						}

					}

					if (img1 != null) {
						img1.src = image1;// img1
					}
					if (j < items.length - 1 && items[j + 1].nodeName == "DL") {
						if (img2 != null) {
							img2.src = image3;// img3
						}
						items[j].onclick = function() {
							
							var cnss = this.childNodes;
							var img11 = null, img22 = null;
							for (var k = 0; k < cnss.length; k++) {
								if (img11 == null) {
									if (cnss[k].nodeName == "IMG") {
										img11 = cnss[k];
									}
								} else if (img22 == null) {
									if (cnss[k].nodeName == "IMG") {
										img22 = cnss[k];
									}
								} else {
									break;
								}
							}
							
							
							if (this.nextSibling.style.display == "none") {
								this.nextSibling.style.display = "block";
										img11.src=image2;
										img22.src=image4;
								
								
							} else {
								this.nextSibling.style.display = "none";
								img11.src=image1;
								img22.src=image3;
							}
						};
						initDlRoot(items[j + 1], image1, image2, image3,
								image4, image5);
						items[j + 1].style.display = "none";

					} else {
						if (img2 != null) {
							img2.src = image5;// img5
						}
					}
					break;
				}
			}
		}
	}
}