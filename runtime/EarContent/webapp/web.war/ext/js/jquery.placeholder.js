jQuery(function () {
	if (!("placeholder" in document.createElement("input"))) { 
		jQuery(":input[placeholder]").each(function () {
			var $this = jQuery(this);
			var pos = $this.offset();
			if (!this.id) this.id = "jQueryVirtual_" + this.name;
			if (this.id) {
				if (jQuery.browser.version  < 8) {
					$this.after("<label for='" + this.id + "' id='jQueryVirtual_label_" + this.id + "' class='absolute'>" + $this.attr("placeholder") + "</label>");
					$("#jQueryVirtual_label_" + this.id).css({"left":(pos.left+5), "margin-top":3, "width":$this.width()});
				}
				else {
					$this.after("<label for='" + this.id + "' id='jQueryVirtual_label_" + this.id + "' style='left:" + (pos.left+5) + "px;margin-top:2px' class='absolute'>" + $this.attr("placeholder") + "</label>");
				}
			}
		}).focus(function () {
			var $this = jQuery(this);
			$this.addClass("focusbox");
			jQuery("#jQueryVirtual_label_" + $this.attr("id")).hide();
		}).blur(function () {
			var $this = jQuery(this);
			$this.removeClass("focusbox");
			if(!jQuery.trim($this.val())) jQuery("#jQueryVirtual_label_" + $this.attr("id")).show();
			else jQuery("#jQueryVirtual_label_" + $this.attr("id")).hide();
		}).trigger("blur");
	}
});