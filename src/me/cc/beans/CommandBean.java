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
	MainControlBean mainControlBean;
	ArrayList<AnyAnswer> dummy_ans;

	@PostConstruct
	public void init() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		mainControlBean = (MainControlBean) facesContext.getApplication().createValueBinding("#{mainControlBean}").getValue(facesContext);
		dummy_ans = AnyAnswer.listFactory();
	}

	public String compAll() {
		mainControlBean.fileREST.sglCall("recompute_all", "whatever");
		mainControlBean.fileREST.getDoc(mainControlBean.getPath(), mainControlBean.getKind());
		return "trainer";
	}

	public String mixCorpus() {
		String which = "this";
		mainControlBean.setLogs(mainControlBean.annotationREST.sglCall("mix_corpus", which));
		return "trainer";
	}

	public String startTraining() {
		String which = "this";
		mainControlBean.setLogs(mainControlBean.annotationREST.sglCall("start_training", which));
		return "trainer";
	}

	public String stopTraining() {
		String which = "this";
		mainControlBean.setLogs(mainControlBean.annotationREST.sglCall("stop_training", which));
		return "trainer";
	}

	public String migrateModel() {
		String which = "this";
		mainControlBean.setLogs(mainControlBean.annotationREST.sglCall("migrate_model", which));
		return "trainer";
	}

	public String scienceMap() {
		String which = "this";
		mainControlBean.setLogs(mainControlBean.scienceREST.sglCall("science_map", which));
		return "trainer";
	}

	public String scienceCoords() {
		String which = "this";
		mainControlBean.setLogs(mainControlBean.scienceREST.sglCall("science_coords", which));
		return "trainer";
	}

	public String scienceVideo() {
		String which = "this";
		mainControlBean.setLogs(mainControlBean.scienceREST.sglCall("science_video", which));
		return "trainer";
	}

	public String logs(String which) {
		mainControlBean.logs_which = which;
		mainControlBean.setLogs(mainControlBean.annotationREST.getLogs(which));
		return "log";
	};

	public String annotation_around() {
		mainControlBean.annotationREST.stdCall("annotation_around", mainControlBean.getSpot(), dummy_ans, mainControlBean.annotationSets,
				PythonClient.AnyAnswerList_Type);
		mainControlBean.fileREST.getDoc(mainControlBean.getPath(), mainControlBean.getKind());
		return "reader";
	}

	public String annotation_from_here() {
		mainControlBean.annotationREST.stdCall("annotation_from_here", mainControlBean.getSpot(), dummy_ans, mainControlBean.annotationSets,
				PythonClient.AnyAnswerList_Type);
		mainControlBean.fileREST.getDoc(mainControlBean.getPath(), mainControlBean.getKind());
		return "reader";
	}

	public String take_it_as_is() {
		mainControlBean.annotationREST.stdCall("take_it_as_is", mainControlBean.getSpot(), dummy_ans, mainControlBean.annotationSets,
				PythonClient.AnyAnswerList_Type);
		mainControlBean.fileREST.getDoc(mainControlBean.getPath(), mainControlBean.getKind());
		return "reader";

	}

	public String zero_annotation_selection_first_corpus() {
		mainControlBean.annotationREST.stdCall("zero_annotation_selection_first_corpus", mainControlBean.getSpot(), dummy_ans,
				mainControlBean.annotationSets, PythonClient.AnyAnswerList_Type);
		mainControlBean.fileREST.getDoc(mainControlBean.getPath(), mainControlBean.getKind());
		FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("text");
		return "reader";
	}

	public String annotation_in_between() {
		mainControlBean.annotationREST.stdCall("annotation_in_between", mainControlBean.getSpot(), dummy_ans, mainControlBean.annotationSets,
				PythonClient.AnyAnswerList_Type);
		mainControlBean.fileREST.getDoc(mainControlBean.getPath(), mainControlBean.getKind());
		return "reader";
	}

	public String zero_annotation_selection_second_corpus() {
		mainControlBean.annotationREST.stdCall("zero_annotation_selection_second_corpus", mainControlBean.getSpot(), dummy_ans,
				mainControlBean.annotationSets, PythonClient.AnyAnswerList_Type);
		mainControlBean.fileREST.getDoc(mainControlBean.getPath(), mainControlBean.getKind());
		return "reader";
	}

	public String trash() {
		return "reader";
	}

	public String migrateCorpus() {
		String which = "this";
		mainControlBean.setLogs(mainControlBean.annotationREST.sglCall("migrate_corpus", which));
		return "reader";
		}

	public String more_kind(String kind, int no) {
		System.out.println("add more " + kind + " set number " + no);
		int size_up_to_now = mainControlBean.annotationSets.get(no).size();
		mainControlBean.annotationSets.get(no).add(new Tag(no, size_up_to_now, kind, 0, mainControlBean.textlen - 1));
		annotationTagsFactory.renumerate(mainControlBean.annotationSets);
		return "manipulator";

	}

	public String less_kind(int no, int _i, String kind) {
		System.out.println("delete " + kind + " set " + no + " index " + _i);
		mainControlBean.annotationSets.get(no).remove(_i);
		mainControlBean.annotationSets.removeIf(p -> p.isEmpty());
		annotationTagsFactory.renumerate(mainControlBean.annotationSets);
		return "manipulator";
	}

	public String more_set(String kind) {
		System.out.println("add more " + kind + " as new set ");
		int size_up_to_now = mainControlBean.annotationSets.size();
		ArrayList<Tag> annotationSet = new ArrayList<Tag>();
		annotationSet.add(new Tag(size_up_to_now, 0, kind, 0, mainControlBean.textlen - 1));
		mainControlBean.annotationSets.add(annotationSet);
		annotationTagsFactory.renumerate(mainControlBean.annotationSets);
		return "manipulator";
	}

	public String less_set(int no, int _i, String kind) {
		annotationTagsFactory.renumerate(mainControlBean.annotationSets);
		return "manipulator";
	}
}