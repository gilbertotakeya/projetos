#pragma checksum "C:\Users\Junior\source\repos\Marvel\Marvel\Views\Home\Quadrinhos.cshtml" "{ff1816ec-aa5e-4d10-87f7-6f4963833460}" "192fd96d93d9e673e6d1aae90f40c4dced8a1fa3"
// <auto-generated/>
#pragma warning disable 1591
[assembly: global::Microsoft.AspNetCore.Razor.Hosting.RazorCompiledItemAttribute(typeof(AspNetCore.Views_Home_Quadrinhos), @"mvc.1.0.view", @"/Views/Home/Quadrinhos.cshtml")]
[assembly:global::Microsoft.AspNetCore.Mvc.Razor.Compilation.RazorViewAttribute(@"/Views/Home/Quadrinhos.cshtml", typeof(AspNetCore.Views_Home_Quadrinhos))]
namespace AspNetCore
{
    #line hidden
    using System;
    using System.Collections.Generic;
    using System.Linq;
    using System.Threading.Tasks;
    using Microsoft.AspNetCore.Mvc;
    using Microsoft.AspNetCore.Mvc.Rendering;
    using Microsoft.AspNetCore.Mvc.ViewFeatures;
#line 1 "C:\Users\Junior\source\repos\Marvel\Marvel\Views\_ViewImports.cshtml"
using Marvel;

#line default
#line hidden
#line 2 "C:\Users\Junior\source\repos\Marvel\Marvel\Views\_ViewImports.cshtml"
using Marvel.Models;

#line default
#line hidden
    [global::Microsoft.AspNetCore.Razor.Hosting.RazorSourceChecksumAttribute(@"SHA1", @"192fd96d93d9e673e6d1aae90f40c4dced8a1fa3", @"/Views/Home/Quadrinhos.cshtml")]
    [global::Microsoft.AspNetCore.Razor.Hosting.RazorSourceChecksumAttribute(@"SHA1", @"c05012abc27bfe210815613e731ed4d2a214400b", @"/Views/_ViewImports.cshtml")]
    public class Views_Home_Quadrinhos : global::Microsoft.AspNetCore.Mvc.Razor.RazorPage<IEnumerable<Marvel.Models.ConsultaMarvelQuadrinhoNivel3ViewModel>>
    {
        #pragma warning disable 1998
        public async override global::System.Threading.Tasks.Task ExecuteAsync()
        {
            BeginContext(74, 2, true);
            WriteLiteral("\r\n");
            EndContext();
#line 3 "C:\Users\Junior\source\repos\Marvel\Marvel\Views\Home\Quadrinhos.cshtml"
  
    ViewData["Title"] = "Personagens Marvel";

#line default
#line hidden
            BeginContext(130, 6, true);
            WriteLiteral("\r\n<h1>");
            EndContext();
            BeginContext(137, 17, false);
#line 7 "C:\Users\Junior\source\repos\Marvel\Marvel\Views\Home\Quadrinhos.cshtml"
Write(ViewData["Title"]);

#line default
#line hidden
            EndContext();
            BeginContext(154, 28, true);
            WriteLiteral("</h1>\r\n\r\n<div class=\"row\">\r\n");
            EndContext();
#line 10 "C:\Users\Junior\source\repos\Marvel\Marvel\Views\Home\Quadrinhos.cshtml"
     foreach (var item in Model)
    {

#line default
#line hidden
            BeginContext(223, 150, true);
            WriteLiteral("        <div class=\"col-md-3 d-flex align-items-stretch\">\r\n            <div class=\"card\" style=\"width: 18rem;max-width: 20rem;\">\r\n                <img");
            EndContext();
            BeginWriteAttribute("src", " src=\"", 373, "\"", 407, 1);
#line 14 "C:\Users\Junior\source\repos\Marvel\Marvel\Views\Home\Quadrinhos.cshtml"
WriteAttributeValue("", 379, item.thumbnail.completePath, 379, 28, false);

#line default
#line hidden
            EndWriteAttribute();
            BeginContext(408, 21, true);
            WriteLiteral(" class=\"card-img-top\"");
            EndContext();
            BeginWriteAttribute("alt", " alt=\"", 429, "\"", 446, 1);
#line 14 "C:\Users\Junior\source\repos\Marvel\Marvel\Views\Home\Quadrinhos.cshtml"
WriteAttributeValue("", 435, item.title, 435, 11, false);

#line default
#line hidden
            EndWriteAttribute();
            BeginContext(447, 87, true);
            WriteLiteral(">\r\n                <div class=\"card-body\">\r\n                    <h5 class=\"card-title\">");
            EndContext();
            BeginContext(535, 10, false);
#line 16 "C:\Users\Junior\source\repos\Marvel\Marvel\Views\Home\Quadrinhos.cshtml"
                                      Write(item.title);

#line default
#line hidden
            EndContext();
            BeginContext(545, 48, true);
            WriteLiteral("</h5>\r\n                    <p class=\"card-text\">");
            EndContext();
            BeginContext(594, 16, false);
#line 17 "C:\Users\Junior\source\repos\Marvel\Marvel\Views\Home\Quadrinhos.cshtml"
                                    Write(item.description);

#line default
#line hidden
            EndContext();
            BeginContext(610, 81, true);
            WriteLiteral("</p>\r\n                </div>\r\n            </div>\r\n        </div>               \r\n");
            EndContext();
#line 21 "C:\Users\Junior\source\repos\Marvel\Marvel\Views\Home\Quadrinhos.cshtml"
    }

#line default
#line hidden
            BeginContext(698, 6, true);
            WriteLiteral("</div>");
            EndContext();
        }
        #pragma warning restore 1998
        [global::Microsoft.AspNetCore.Mvc.Razor.Internal.RazorInjectAttribute]
        public global::Microsoft.AspNetCore.Mvc.ViewFeatures.IModelExpressionProvider ModelExpressionProvider { get; private set; }
        [global::Microsoft.AspNetCore.Mvc.Razor.Internal.RazorInjectAttribute]
        public global::Microsoft.AspNetCore.Mvc.IUrlHelper Url { get; private set; }
        [global::Microsoft.AspNetCore.Mvc.Razor.Internal.RazorInjectAttribute]
        public global::Microsoft.AspNetCore.Mvc.IViewComponentHelper Component { get; private set; }
        [global::Microsoft.AspNetCore.Mvc.Razor.Internal.RazorInjectAttribute]
        public global::Microsoft.AspNetCore.Mvc.Rendering.IJsonHelper Json { get; private set; }
        [global::Microsoft.AspNetCore.Mvc.Razor.Internal.RazorInjectAttribute]
        public global::Microsoft.AspNetCore.Mvc.Rendering.IHtmlHelper<IEnumerable<Marvel.Models.ConsultaMarvelQuadrinhoNivel3ViewModel>> Html { get; private set; }
    }
}
#pragma warning restore 1591
