(function($) {

    $.fn.pager = function(options) {

        var opts = $.extend({}, $.fn.pager.defaults, options);

        return this.each(function() {

        // empty out the destination element and then render out the pager with the supplied options
            $(this).empty().append(renderpager(parseInt(options.pagenumber), parseInt(options.pagecount), parseInt(options.totalcount), options.buttonClickCallback));

            // specify correct cursor activity
            //$('.pages li').mouseover(function() { document.body.style.cursor = "pointer"; }).mouseout(function() { document.body.style.cursor = "auto"; });
        });
    };

    // render and return the pager with the supplied options
    function renderpager(pagenumber, pagecount, totalcount, buttonClickCallback) {

        var pageListSize = 10;        
        var startPoint = 1;
        var endPoint = 10;
        var nTotalPage = Math.round(totalcount/pagecount) ==0 ? 1 : Math.round(totalcount/pagecount);                      
        var nTemp = Math.floor((pagenumber-1) / pageListSize)*pageListSize; //몫
        
		startPoint = nTemp + 1;
		endPoint = nTemp +pageListSize ;
		
		
		//setting last page        
        if (endPoint > nTotalPage) {
            endPoint = nTotalPage;
        }
		//console.log(startPoint,endPoint,nTotalPage);
        // setup $pager to hold render
        var $pager = $('<ul class="page-list"></ul>');	  
        $pager.append(renderButton('first', pagenumber, pagecount, nTotalPage, startPoint ,  buttonClickCallback));
        $pager.append(renderButton('prev', pagenumber, pagecount, nTotalPage, startPoint , buttonClickCallback));
        
        // loop thru visible pages and render buttons
        for (var page = startPoint; page <= endPoint; page++) {

            var currentButton = $('<li class="jpage"><a href="#">' + (page) + '</a></li>');

            page == pagenumber ? currentButton.addClass('now') : currentButton.click(function() { buttonClickCallback(this.firstChild.firstChild.data); });
            currentButton.appendTo($pager);
        }

        // render in the next and last buttons before returning the whole rendered control back.
        $pager.append(renderButton('next', pagenumber, pagecount, nTotalPage, endPoint , buttonClickCallback));
        $pager.append(renderButton('last', pagenumber, pagecount, nTotalPage, endPoint , buttonClickCallback));

        return $pager;
    }

    // renders and returns a 'specialized' button, ie 'next', 'previous' etc. rather than a page number button
    function renderButton(buttonLabel, pagenumber, pagecount, totalpage, nextPoint , buttonClickCallback) {
		
		
        var $Button = $('<li class="pgBtn">' + buttonLabel + '</li>');
		
		switch (buttonLabel) {
            case "first":
                $Button = $('<li class=""><a href="#"><<</a></li>'); //btn_first.gif
                break;
            case "prev":
                $Button = $('<li class=""><a href="#"><</a></li>'); //<img src="../ext/img/btn_prev.gif" alt="prev" />
                break;
            case "next":
                $Button = $('<li class=""><a href="#">></a></li>'); //<img src="../ext/img/btn_next.gif" alt="next" />
                break;
            case "last":
                $Button = $('<li class=""><a href="#">>></a></li>'); //<img src="../ext/img/btn_last.gif" alt="last" style="width:23px;height:23px;" />
                break;
        }

        var destPage = 1;
        // work out destination page for required button type
        switch (buttonLabel) {
            case "first":
                destPage = 1;
                break;
            case "prev":
                destPage = nextPoint-10;
                break;
            case "next":
                destPage = nextPoint+1;
                break;
            case "last":
                destPage = totalpage;
                break;
        }

        // disable and 'grey' out buttons if not needed.
        if (buttonLabel == "first" || buttonLabel == "prev") {
        	
        	if(pagenumber == 1 || destPage < 1 || nextPoint == 1 ) {
        		//$Button.addClass('pgEmpty');
        		$Button ="";
        	} else{
        		$Button.addClass('pgBtn');
        		$Button.click(function() { buttonClickCallback(destPage); });
        	}
        }
        else {

            if(pagenumber >= totalpage || destPage > totalpage || nextPoint == totalpage ) {
            	//$Button.addClass('pgEmpty');
            	$Button ="";
            } else {
            	$Button.addClass('pgBtn');
            	$Button.click(function() { buttonClickCallback(destPage); });
            }
        }

        return $Button;
    }

    // pager defaults. hardly worth bothering with in this case but used as placeholder for expansion in the next version
    $.fn.pager.defaults = {
        pagenumber: 1,
        pagecount: 1
    };

})(jQuery);





