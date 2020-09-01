$(function() {

 	//lazyLoad
 	start();
 	$(window).on('scroll', function() {
 		start();
 	});

 	/*===图片轮播===================================================*/
 	var swiperBanner = new Swiper('.index-banner-swi .swiper-container', {
 		// pagination: '.index-banner-swi .swiper-pagination',
 		pagination: {
	        el: '.swiper-pagination',
	        clickable: true,
	        type: 'fraction',
	        formatFractionCurrent: function (number) {
	        	return '0'+number; 
	        },
	    	formatFractionTotal: function (number) {
	    		return '0'+number; 
	 		},

	  	},
 		paginationClickable: '.index-banner-swi .swiper-pagination',
 		autoplay: {
 			delay: 7000,
		    stopOnLastSlide: false,
		    disableOnInteraction: true,
 		},
 		autoplayDisableOnInteraction: false,
 		effect: 'cube',
 		grabCursor: true,
 		cube: {
 		        shadow: true,
 		        slideShadows: true,
 		        shadowOffset: 20,
 		        shadowScale: 0.94
 		},
		paginationType: 'fraction',
 		loop: true, // 循环模式选项
 	});
 	//弹出窗口
 	openurl = $(".link").text();
 	$('#zxzx').click(function() {
 		openWin(openurl);
 	});
 	//滑动	 
 	var height = document.documentElement.clientHeight || document.body.clientHeight;
 	var thisS = [];
 	var titLen = $(".title").length;
 	var scrTop = document.documentElement.scrollTop || document.body.scrollTop;
 	for (var i = 0; i < titLen; i++) {
 		thisS[i] = $(".title").eq(i).offset().top - scrTop;
 		if ((height - thisS[i]) >= 130) {
 			if (!$(".title").eq(i).hasClass("animated lineTran")) {
 				$(".title").eq(i).addClass("animated lineTran").removeClass("animate");
 				$(".title").eq(i).next(".animateBox").removeClass("animate-up").addClass("animated");
 			}
 		} else {
 			if ((height < thisS[i])) {
 				if ($(".title").eq(i).hasClass("animated lineTran")) {
 					$(".title").eq(i).addClass("animate").removeClass("animated lineTran");
 					$(".title").eq(i).next(".animateBox").removeClass("animated").addClass("animate-up");
 				}

 			}
 		}
 	}
	//nav
	
	window.onscroll = function() {
		var j = document.documentElement.scrollTop || document.body.scrollTop;
		if (j > 0) {
			if (!$(".zdHeader").hasClass("active")) {
				$(".zdHeader").addClass("active")
			}
			$("#backTop").fadeIn();
		} else {
			if (j === 0) {
				$(".zdHeader").removeClass("active")
			}
			$("#backTop").fadeOut();
		}
		for (var i = 0; i < titLen; i++) {
			thisS[i] = $(".title").eq(i).offset().top - j;
			if ((height - thisS[i]) >= 130) {
				if (!$(".title").eq(i).hasClass("animated lineTran")) {
					$(".title").eq(i).addClass("animated lineTran").removeClass("animate");
					$(".title").eq(i).next(".animateBox").removeClass("animate-up").addClass("animated");
				}
			} else {
				if ((height < thisS[i])) {
					if ($(".title").eq(i).hasClass("animated lineTran")) {
						$(".title").eq(i).addClass("animate").removeClass("animated lineTran");
						$(".title").eq(i).next(".animateBox").removeClass("animated").addClass("animate-up");
					}
	
				}
			}
		}
		if ($('.indexModule1 .box').length > 0) {
			if (isShow($(".indexModule1 .box"))) {
				$(".indexModule1 .box").addClass("show");
			} else {
				$(".indexModule1 .box").removeClass("show");
			}
		}
		if ($('.indexModule6').length > 0) {
			if (isShow($(".indexModule6 .animateBox"))) {
				if (hasfirst) {
					$(".indexModule6 .animateBox").addClass("first");
					hasfirst = false;
					setTimeout(function() {
						$(".indexModule6 .animateBox").removeClass("first");
					}, 3000);
				}
			} else {
				hasfirst = true;
				$(".indexModule6 .animateBox").removeClass("first");
			}
		}
		if (isCount && $('.indexNum').length > 0) {
			if (isShow($(".indexNum"))) {
				$('.timer').each(count);
				isCount = false;
			};
		};
	
	};
 	//计数
 	var winWidth = document.body.clientWidth;
 	var isCount = true;
 	if (isCount && $('.indexNum').length > 0) {
 		if (isShow($(".indexNum"))) {
 			$(".timer").each(function(i) {
 				var num = $(this).attr("data-to");
 				$(this).animateNumbers(num, false, 1000, "linear");
 				isCount = false;
 			});
 		}
 	};
 	var hasfirst = true;
 	if ($(".indexModule6 .animateBox").length > 0) {
 		if (isShow($(".indexModule6 .animateBox")) && hasfirst) {
 			$(".indexModule6 .animateBox").addClass("first");
 			hasfirst = false;
 			setTimeout(function() {
 				$(".indexModule6 .animateBox").removeClass("first");
 			}, 3000);
 		} else {
 			hasfirst = true;
 		}
 	};
 	if ($('.indexModule1 .box').length > 0) {
 		if (isShow($(".indexModule1 .box"))) {
 			$(".indexModule1 .box").addClass("show");
 		} else {
 			$(".indexModule1 .box").removeClass("show");
 		}
 	}
	//form
	$("#formSubBtn").click(function() {
		// if (!$(".nameInput").val()) {
		// 	layer.msg("请填写您的姓名！");
		// 	$(".nameInput").focus()
		// 	return false;
		// }
		if (!$(".telInput").val()) {
			layer.msg("请填写您的手机号！");
			$(".telInput").focus()
			return false;
		}
		if (!$(".requireInput").val()) {
			layer.msg("请填写您的需求！");
			$(".requireInput").focus()
			return false;
		}
		$.ajax({
			type: "POST",
			url: '/index.php?c=form_dibubiaodan',
			data: $('#asideForm').serialize(),
			async: false,
			error: function(request) {
				layer.msg("提交失败！")
			},
			success: function(res) {
				res = JSON.parse(res);
				if (res.code == "0") {
					layer.msg(res.msg);
				}
				if (res.code == "1") {
					$("#asideForm .nameInput").val('');
					$("#asideForm .telInput").val('');
					$("#asideForm .requireInput").val('');
					$(".formSuc").stop().fadeIn()
				}
			}
		});
	});
	$(".formSuc .closeBtn").click(function() {
		$(".formSuc").stop().fadeOut();
	})
	//backtop
	$("#backTop").click(function() {
		$('html , body').animate({
			scrollTop: 0
		}, 'slow');
	});
	//tabchange
	tabChange(".indexModule2 .tabBar", ".indexModule2 .tabContent", "active");
	function seteveryting() {
		var bodyw = $(".fixbug").width();
		var el = $(".fixbug ul");
		$(".fixbug .tempWrap").css("width", bodyw);
		el.css("width", bodyw);
		$(".ul-box ").css("height", $('.fixbug').height());
	}
	$('.fixbug').each(function() {
		var abc = 0;
		var el2 = $(this);
		var bodyw2 = $(".fixbug").width();
		var el = $(this).find('ul');
		var linum = el.length;
		$(window).resize(function() {
			bodyw2 = $(".fixbug").width();
			el2.find(".ul-bd").stop().animate({
				left: -bodyw2 * abc
			}, 1000);
			el2.find(".ul-bd").css("width", bodyw2 * linum);
		})
		$(this).slide({
			mainCell: ".ul-bd",
			effect: "left",
			autoPage: true,
			delayTime: 800,
			endFun: function(i, c) {
				abc = i;
			}
		});
	})
	jQuery(".case").slide({
		titCell: ".ul-wrap li",
		mainCell: ".ul-box",
		effect: "fold"
	});
	seteveryting();
	$(".ul-box").css("opacity", "1");
	$(window).resize(function() {
		seteveryting();
	});
	// 轮播
	$(".preview .swiper-slide").eq(0).addClass("active-nav");
	var viewSwiper = new Swiper('.view .swiper-container', {
		effect: 'fade',
		slidesPerView: 1,
		// coverflow: {
		// 	rotate: 0, 
		// 	stretch: 160, 
		// 	depth: 150,
		// 	modifier: 3,
		// 	slideShadows: false
		// },
		onSlideChangeStart: function() {
			updateNavPosition();
		}
	});
	// $('.view .arrow-left,.preview .arrow-left').on('click', function(e) {
	// 	e.preventDefault()
	// 	if (viewSwiper.activeIndex == 0) {
	// 		//viewSwiper.slideTo(viewSwiper.slides.length - 1, 1000);
	// 		return
	// 	}
	// 	viewSwiper.slidePrev()
	// })
	// $('.view .arrow-right,.preview .arrow-right').on('click', function(e) {
	// 	e.preventDefault()
	// 	if (viewSwiper.activeIndex == viewSwiper.slides.length - 1) {
	// 		//viewSwiper.slideTo(0, 1000);
	// 		return
	// 	}
	// 	viewSwiper.slideNext()
	// })
	
	var previewSwiper = new Swiper('.preview .swiper-container', {
		visibilityFullFit: true,
		slidesPerView: 'auto',
		noSwiping: true
	})
	$(".indexModule2 .preview .swiper-slide").hover(function() {
		viewSwiper.slideTo($(this).index());
		previewSwiper.slideTo($(this).index());
		updateNavPosition();
	}, function() {
	
	});
	// $(".indexModule2 .swi").hover(function() {
	// 	previewSwiper.stopAutoplay();
	// 	viewSwiper.stopAutoplay();
	// }, function() {
	// 	previewSwiper.startAutoplay();
	// 	viewSwiper.startAutoplay();
	// })
	
	function updateNavPosition() {
		$('.preview .active-nav').removeClass('active-nav')
		var activeNav = $('.preview .swiper-slide').eq(viewSwiper.activeIndex).addClass('active-nav');
		if (!activeNav.hasClass('swiper-slide-visible')) {
			if (activeNav.index() > previewSwiper.activeIndex) {
				var thumbsPerNav = Math.floor(previewSwiper.width / activeNav.width()) - 1
				previewSwiper.slideTo(activeNav.index() - thumbsPerNav)
			} else {
				previewSwiper.slideTo(activeNav.index())
			}
		}
	}
	
	//var partnerSwi = new Swiper('.partnerSwi .swiper-container', {
	//		effect : 'fade',
	//      pagination: '.partnerSwi .swiper-pagination',  
	//      autoplay: 7000,
	//		autoplayDisableOnInteraction : false,
	//      paginationClickable: true
	//  });
	jQuery(".join").slide({
		mainCell: ".join-inner",
		// titCell: ".hd li",
		effect: "fold",
		delayTime: 1000,
		autoPlay: true,
		interTime: 5500
	});
	
	
	if (640 < winWidth) {
		$(".telitem").hover(function() {
			$("#telBox").addClass("active");
			$(".telitem").addClass("cur");
		}, function() {
			$("#telBox").removeClass("active");
			$(".telitem").removeClass("cur");
		});
	} else {
		$(".telitem").click(function() {
			if ($("#telBox").css("height") == '0px') {
				$(".telitem").addClass("cur");
				$("#telBox").addClass("active");
			}
		});
		$(document).bind('click', function(e) {
			if ($("#telBox").css("height") != "0px") {
				var e = e || window.event; //浏览器兼容性   
				var elem = e.target || e.srcElement;
				while (elem) { //循环判断至跟节点，防止点击的是div子元素   
					if (elem.id && elem.id == 'telBox') {
						return;
					}
					elem = elem.parentNode;
				}
				$(".telitem").removeClass("cur");
				$("#telBox").removeClass('active'); //点击的不是div或其子元素   
			}
		});
	}
	//form
	$("#asideFormBtn").click(function() {
		$(".footerForm").stop().fadeIn();
		$("#asideFormBtn").stop().fadeOut();
	})
	$(".footerForm #formClose").click(function() {
		$(".footerForm").stop().fadeOut();
		$("#asideFormBtn").stop().fadeIn();
	});
	
 });
 //弹出窗口
 function openWin(url) {
 	var u = url;
 	var iWidth = 750; //弹出窗口的宽度; 
 	var iHeight = 634; //弹出窗口的高度; 
 	//获得窗口的垂直位置 
 	var iTop = (window.screen.availHeight - 30 - iHeight) / 2;
 	//获得窗口的水平位置 
 	var iLeft = (window.screen.availWidth - 10 - iWidth) / 2;
 	console.log(iHeight);
 	window.open(u, 'newwindow', 'height=' + iHeight + ', width=' + iWidth + ', top=' + iTop + ',left=' + iLeft +
 		', toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no');
 }


 //计数
 $.fn.countTo = function(options) {
 	options = options || {};

 	return $(this).each(function() {
 		// set options for current element
 		var settings = $.extend({}, $.fn.countTo.defaults, {
 			from: $(this).data('from'),
 			to: $(this).data('to'),
 			speed: $(this).data('speed'),
 			refreshInterval: $(this).data('refresh-interval'),
 			decimals: $(this).data('decimals')
 		}, options);

 		// how many times to update the value, and how much to increment the value on each update
 		var loops = Math.ceil(settings.speed / settings.refreshInterval),
 			increment = (settings.to - settings.from) / loops;

 		// references & variables that will change with each update
 		var self = this,
 			$self = $(this),
 			loopCount = 0,
 			value = settings.from,
 			data = $self.data('countTo') || {};

 		$self.data('countTo', data);

 		// if an existing interval can be found, clear it first
 		if (data.interval) {
 			clearInterval(data.interval);
 		}
 		data.interval = setInterval(updateTimer, settings.refreshInterval);

 		// initialize the element with the starting value
 		render(value);

 		function updateTimer() {
 			value += increment;
 			loopCount++;

 			render(value);

 			if (typeof(settings.onUpdate) == 'function') {
 				settings.onUpdate.call(self, value);
 			}

 			if (loopCount >= loops) {
 				// remove the interval
 				$self.removeData('countTo');
 				clearInterval(data.interval);
 				value = settings.to;

 				if (typeof(settings.onComplete) == 'function') {
 					settings.onComplete.call(self, value);
 				}
 			}
 		}

 		function render(value) {
 			var formattedValue = settings.formatter.call(self, value, settings);
 			$self.html(formattedValue);
 		}
 	});
 };

 $.fn.countTo.defaults = {
 	from: 0, // the number the element should start at
 	to: 0, // the number the element should end at
 	speed: 1000, // how long it should take to count between the target numbers
 	refreshInterval: 100, // how often the element should be updated
 	decimals: 0, // the number of decimal places to show
 	formatter: formatter, // handler for formatting the value before rendering
 	onUpdate: null, // callback method for every time the element is updated
 	onComplete: null // callback method for when the element finishes updating
 };

 function formatter(value, settings) {
 	return value.toFixed(settings.decimals);
 }

 // start all the timers


 function count(options) {
 	var $this = $(this);
 	options = $.extend({}, options || {}, $this.data('countToOptions') || {});
 	$this.countTo(options);
 }
 (function($) {
 	$.fn.animateNumbers = function(stop, commas, duration, ease) {
 		return this.each(function() {
 			var $this = $(this);
 			var start = parseInt($this.text().replace(/,/g, ""));
 			commas = (commas === undefined) ? true : commas;
 			$({
 				value: start
 			}).animate({
 				value: stop
 			}, {
 				duration: duration == undefined ? 1000 : duration,
 				easing: ease == undefined ? "swing" : ease,
 				step: function() {
 					$this.text(Math.floor(this.value));
 					if (commas) {
 						$this.text($this.text().replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,"));
 					}
 				},
 				complete: function() {
 					if (parseInt($this.text()) !== stop) {
 						$this.text(stop);
 						if (commas) {
 							$this.text($this.text().replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,"));
 						}
 					}
 				}
 			});
 		});
 	};
 })(jQuery);








 
 //$(document).ready(function() {
 	//	if($(".indexNum").length>0 && winWidth>640){
 	//		setTimeout(function(){
 	//			$(".footerForm").stop().fadeIn();
 	//			$("#asideFormBtn").stop().fadeOut();
 	//		},2000);
 	//	}		
 //});
 //tabchange
 function tabChange(tab, con, ac) {
 	$(tab).click(function() {
 		var i = $(this).index();
 		$(this).addClass(ac).siblings().removeClass(ac);
 		$(con).eq(i).addClass(ac).siblings().removeClass(ac);
 	});
 }
 
 function start() {
 	//.not('[data-isLoaded]')选中已加载的图片不需要重新加载
 	$('img').not('[data-isLoaded]').each(function() {
 		var $node = $(this)
 		if (isShow($node)) {
 			loadImg($node)
 		}
 	})
 }

 //判断一个元素是不是出现在窗口(视野)
 function isShow($node) {
 	return $node.offset().top <= $(window).height() + $(window).scrollTop() + 200
 }
 //加载图片
 function loadImg($img) {
 	//.attr(值)
 	//.attr(属性名称,值)
 	$img.attr('src', $img.attr('data-src')) //把data-src的值 赋值给src
 	$img.attr('data-isLoaded', 1) //已加载的图片做标记
 }
