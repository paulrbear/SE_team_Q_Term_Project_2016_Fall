package mdconverter;
import java.util.Iterator;


public class FancyVisitor implements Visitor{
   


   static String htmlCode = 
"<html>\n"
+ "<head>\n"
+ "<style>\n"
+ "font-family:verdana;\n"
+ "font-size:110%;\n"
+ "</style>\n"
+ "<title>\n"
+ "</title>\n"
+ "</head>\n"
+ "<body>\n";
   

   public FancyVisitor(){
      
   }

   public String getDocument(){
      return htmlCode + "</body>\n</html>";
   }

   @Override
   public void visitHeaderNode(HeaderNode hn) {
      Iterator<Node> it = hn.tokens.iterator();
      switch(hn.nodeStyle){
      case H1:
         htmlCode = htmlCode + "<h1 style=\"color:blue;\">";
         while(it.hasNext())   it.next().accept(this);
         htmlCode = htmlCode + "</h1>\n";
         break;
      case H2:
         htmlCode = htmlCode + "<h2 style=\"color:red;\">";
         while(it.hasNext())   it.next().accept(this);
         htmlCode = htmlCode + "</h2>\n";
         break;
      case H3:
         htmlCode = htmlCode + "<h3 style=\"color:green;\">";
         while(it.hasNext())   it.next().accept(this);
         htmlCode = htmlCode + "</h3>\n";
         break;
      case H4:
         htmlCode = htmlCode + "<h4 style=\"color:pink;\">";
         while(it.hasNext())   it.next().accept(this);
         htmlCode = htmlCode + "</h4>\n";
         break;
      case H5:
         htmlCode = htmlCode + "<h5 style=\"color:black;\">";
         while(it.hasNext())   it.next().accept(this);
         htmlCode = htmlCode + "</h5>\n";
         break;
      case H6:
         htmlCode = htmlCode + "<h6 style=\"color:purple;\">";
         while(it.hasNext())   it.next().accept(this);
         htmlCode = htmlCode + "</h6>\n";
         break;
      default:
         System.out.println("error!!");
         break;
      }
   }
   public void visitItemListNode(ItemListNode in) {
      Iterator<Node> it = in.tokens.iterator();
      switch(in.nodeStyle) {
      case Ordered:
         htmlCode = htmlCode + "<ol>";
         // need to handle sub item list
         while (it.hasNext()) it.next().accept(this);
         htmlCode = htmlCode + "</ol>\n";
         break;
      case Unordered:
         htmlCode = htmlCode + "<ul>";
         System.out.println("hello");
         while (it.hasNext()) it.next().accept(this);
         htmlCode = htmlCode + "</ul>\n";
         break;
      default:
         System.out.println("error!!");
         break;
      }
   }
   public void visitBlockNode(BlockNode bn) {
      Iterator<Node> it = bn.tokens.iterator();
      htmlCode = htmlCode + "<p>";
      while (it.hasNext()) it.next().accept(this);
      htmlCode = htmlCode + "</p>\n";
   
   }
   public void visitQBlockNode(QuotedBlockNode qbn) {
      Iterator<Node> it = qbn.tokens.iterator();
      htmlCode = htmlCode + "<q>";
      while (it.hasNext()) it.next().accept(this);
      htmlCode = htmlCode + "</q>\n";
   }

   public void visitBoldToken(BoldToken bt) {
      Iterator<Node> it = bt.tokens.iterator();
      htmlCode = htmlCode + "<strong style=\"font-size:120%\">";
      while (it.hasNext()) it.next().accept(this);
      htmlCode = htmlCode + "</strong>";
   }
   public void visitPlainToken(PlainToken pt) {
      htmlCode = htmlCode + pt.getString();
   }

   @Override
   public void visitItalicToken(ItalicToken it) {
      Iterator<Node> iter = it.tokens.iterator();
      htmlCode = htmlCode + "<em style=\"font-size:120%;\">";
      while (iter.hasNext()) iter.next().accept(this);
      htmlCode = htmlCode + "</em>";
      
   }

   @Override
   public void visitListedItem(ListedItem li) {
      Iterator<Node> it = li.tokens.iterator();
      htmlCode = htmlCode + "<li>";
      while (it.hasNext()) it.next().accept(this);
      htmlCode = htmlCode + "</li>";
      
   }

   @Override
   public void visitCodeBlockNode(CodeBlockNode cbn) {
      htmlCode = htmlCode +"<pre><code>";
      htmlCode = htmlCode +cbn.getString();
      htmlCode = htmlCode +"</code></pre>\n";
   }
}