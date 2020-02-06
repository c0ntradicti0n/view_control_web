package me.cc.beans;
import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import me.cc.model.AnyAnswer;
import me.cc.model.Tag;
import me.cc.model.annotationTagsFactory;
import me.cc.restclient.PythonClient;

@ViewScoped
@ManagedBean(name = "commandBean")
public class CommandBean  implements Serializable {
	CcPyBean ccPyBean;
	ArrayList<AnyAnswer> dummy_ans;

	@PostConstruct
	public void init() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ccPyBean = (CcPyBean) facesContext.getApplication().createValueBinding("#{ccPyBean}").getValue(facesContext);
		dummy_ans = AnyAnswer.listFactory();
	}

	public String compAll() {
		ccPyBean.fileREST.sglCall("recompute_all", "whatever");
		ccPyBean.fileREST.getDoc(ccPyBean.getPath(), ccPyBean.getKind());
		return "trainer";
	}


	public String mixCorpus() {
		String which = "this";
		ccPyBean.setLogs(ccPyBean.annotationREST.sglCall("mix_corpus", which));
		return "trainer";
	}

	public String startTraining() {
		String which = "this";
		ccPyBean.setLogs(ccPyBean.annotationREST.sglCall("start_training", which));
		return "trainer";
	}

	public String stopTraining() {
		String which = "this";
		ccPyBean.setLogs(ccPyBean.annotationREST.sglCall("stop_training", which));
		return "trainer";
	}

	public String migrateModel() {
		String which = "this";
		ccPyBean.setLogs(ccPyBean.annotationREST.sglCall("migrate_model", which));
		return "trainer";
	}

	public String scienceMap() {
		String which = "this";
		ccPyBean.setLogs(ccPyBean.scienceREST.sglCall("science_map", which));
		return "trainer";
	}

	public String scienceCoords() {
		String which = "this";
		ccPyBean.setLogs(ccPyBean.annotationREST.sglCall("science_coords", which));
		return "trainer";
	}

	public String scienceVideo() {
		String which = "this";
		ccPyBean.setLogs(ccPyBean.annotationREST.sglCall("science_video", which));
		return "trainer";
	}

	public String logs(String which) {
		ccPyBean.logs_which = which;
		ccPyBean.setLogs(ccPyBean.annotationREST.getLogs(which));
		return "log";
	};

	public String annotation_around() {
		ccPyBean.annotationREST.stdCall("annotation_around", ccPyBean.getSpot(), dummy_ans, ccPyBean.annotationSets,
				PythonClient.AnyAnswerList_Type);
		ccPyBean.fileREST.getDoc(ccPyBean.getPath(), ccPyBean.getKind());
		return "reader";
	}

	public String annotation_from_here() {
		ccPyBean.annotationREST.stdCall("annotation_from_here", ccPyBean.getSpot(), dummy_ans, ccPyBean.annotationSets,
				PythonClient.AnyAnswerList_Type);
		ccPyBean.fileREST.getDoc(ccPyBean.getPath(), ccPyBean.getKind());
		return "reader";
	}

	public String take_it_as_is() {
		ccPyBean.annotationREST.stdCall("take_it_as_is", ccPyBean.getSpot(), dummy_ans, ccPyBean.annotationSets,
				PythonClient.AnyAnswerList_Type);
		ccPyBean.fileREST.getDoc(ccPyBean.getPath(), ccPyBean.getKind());
		return "reader";

	}

	public String zero_annotation_selection_first_corpus() {
		ccPyBean.annotationREST.stdCall("zero_annotation_selection_first_corpus", ccPyBean.getSpot(), dummy_ans,
				ccPyBean.annotationSets, PythonClient.AnyAnswerList_Type);
		ccPyBean.fileREST.getDoc(ccPyBean.getPath(), ccPyBean.getKind());
		FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("text");
		return "reader";
	}

	public String annotation_in_between() {
		ccPyBean.annotationREST.stdCall("annotation_in_between", ccPyBean.getSpot(), dummy_ans, ccPyBean.annotationSets,
				PythonClient.AnyAnswerList_Type);
		ccPyBean.fileREST.getDoc(ccPyBean.getPath(), ccPyBean.getKind());
		return "reader";
	}

	public String zero_annotation_selection_second_corpus() {
		ccPyBean.annotationREST.stdCall("zero_annotation_selection_second_corpus", ccPyBean.getSpot(), dummy_ans,
				ccPyBean.annotationSets, PythonClient.AnyAnswerList_Type);
		ccPyBean.fileREST.getDoc(ccPyBean.getPath(), ccPyBean.getKind());
		return "reader";
	}

	public String trash() {
		return "reader";
	}

	public String migrateCorpus() {
		String which = "this";
		ccPyBean.setLogs(ccPyBean.annotationREST.sglCall("migrate_corpus", which));
		return "reader";
		}

	public String more_kind(String kind, int no) {
		System.out.println("add more " + kind + " set number " + no);
		int size_up_to_now = ccPyBean.annotationSets.get(no).size();
		ccPyBean.annotationSets.get(no).add(new Tag(no, size_up_to_now, kind, 0, ccPyBean.textlen - 1));
		annotationTagsFactory.renumerate(ccPyBean.annotationSets);
		return "manipulator";

	}

	public String less_kind(int no, int _i, String kind) {
		System.out.println("delete " + kind + " set " + no + " index " + _i);
		ccPyBean.annotationSets.get(no).remove(_i);
		ccPyBean.annotationSets.removeIf(p -> p.isEmpty());
		annotationTagsFactory.renumerate(ccPyBean.annotationSets);
		return "manipulator";
	}

	public String more_set(String kind) {
		System.out.println("add more " + kind + " as new set ");
		int size_up_to_now = ccPyBean.annotationSets.size();
		ArrayList<Tag> annotationSet = new ArrayList<Tag>();
		annotationSet.add(new Tag(size_up_to_now, 0, kind, 0, ccPyBean.textlen - 1));
		ccPyBean.annotationSets.add(annotationSet);
		annotationTagsFactory.renumerate(ccPyBean.annotationSets);
		return "manipulator";
	}

	public String less_set(int no, int _i, String kind) {
		annotationTagsFactory.renumerate(ccPyBean.annotationSets);
		return "manipulator";
	}
}